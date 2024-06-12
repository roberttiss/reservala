package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    private String createSQL = "insert into room(roomNumber,type, price, available) values (?, ?, ?, ?)";
    private String readSQL = "select * from room";
    private String updateSQL = "update room set type = ?, price = ?, available = ? where roomNumber = ? ";
    private String updateAvailableSQL = "update room set available = ? where roomNumber = ? ";
    private String deleteSQL = "delete from room where roomNumber = ? ";

    public RoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Room createRoom(Room room){
        jdbcTemplate.update(
                createSQL,
                room.getRoomNumber(),
                room.getType(),
                room.getPrice(),
                room.getAvailable()
        );
        return room;
    }

    public List<Room> readRoom(){
        RowMapper<Room> rowMapper = ((rs, rowNum) -> new Room(
                rs.getInt("roomNumber"),
                rs.getString("type"),
                rs.getBigDecimal("price"),
                rs.getBoolean("available")
        ));
        return jdbcTemplate.query(readSQL, rowMapper);
    }

    public List<Room> readRoomByRoomNumber(Integer roomNumber){
        RowMapper<Room> rowMapper = ((rs, rowNum) -> new Room(
                rs.getInt("roomNumber"),
                rs.getString("type"),
                rs.getBigDecimal("price"),
                rs.getBoolean("available")
        ));

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("select * from room where roomNumber = ?");
                ps.setInt(1,roomNumber);
                return ps;
            }
        };
        return jdbcTemplate.query(psc,rowMapper);
    }

    public Room updateRoom(Room room, Integer roomNumber){
        jdbcTemplate.update(
                updateSQL,
                room.getType(),
                room.getPrice(),
                room.getAvailable(),
                roomNumber
        );
        return room;
    }

    public void setAvailableFalseRoom(Integer roomNumber){
        jdbcTemplate.update(
                updateAvailableSQL,
                false,
                roomNumber
        );
    }

    public void deleteRoom(Integer roomNumber){
        jdbcTemplate.update(deleteSQL, roomNumber);
    }

    public Double getOcupation(){
        return jdbcTemplate.queryForObject("SELECT (COUNT(*) FILTER (WHERE available = false) / COUNT(*)) * 100 FROM room", Double.class);
    }

    public Double countRevenue(){
        return jdbcTemplate.queryForObject("select sum(price) from room", Double.class);
    }

    public Boolean roomExists(Integer roomNumber){
        Integer count = jdbcTemplate.queryForObject("select count(*) from room where roomNumber = ?", Integer.class,roomNumber);
        return count != null && count > 0;
    }

    public boolean roomIsAvalaible(Integer roomNumber){
        Integer count =  jdbcTemplate.queryForObject("select count(*) from room where roomNumber = ? and available = true", Integer.class,roomNumber);
        return count != null && count > 0;
    }

}
