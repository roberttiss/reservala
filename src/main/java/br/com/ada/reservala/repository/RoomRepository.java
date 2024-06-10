package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    private String createSQL = "insert into room(roomNumber,type, price, avalaible) values (?, ?, ?, ?)";
    private String readSQL = "select * from room";
    private String updateSQL = "update room set type = ?, price = ?, avalaible = ? where roomNumber = ? ";
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
                room.getAvalaible()
        );
        return room;
    }

    public List<Room> readRoom(){
        RowMapper<Room> rowMapper = ((rs, rowNum) -> new Room(
                rs.getInt("roomNumber"),
                rs.getString("type"),
                rs.getBigDecimal("price"),
                rs.getBoolean("avalaible")
        ));
        return jdbcTemplate.query(readSQL, rowMapper);
    }

    public Room updateRoom(Room room, Integer roomNumber){
        jdbcTemplate.update(
                updateSQL,
                room.getType(),
                room.getPrice(),
                room.getAvalaible(),
                roomNumber
        );
        return room;
    }

    public void deleteRoom(Integer roomNumber){
        jdbcTemplate.update(deleteSQL, roomNumber);
    }

    public Double getNumberOfRooms(){
        return jdbcTemplate.queryForObject("select count(*) from room", Double.class);
    }

    public Double getNumberOfOccupiedRooms(){
        return jdbcTemplate.queryForObject("select count(*) from room where avalaible = false", Double.class);
    }

    public Double countRevenue(){
        return jdbcTemplate.queryForObject("select sum(price) from room", Double.class);
    }

    public Boolean roomExists(Integer roomNumber){
        Integer count = jdbcTemplate.queryForObject("select count(*) from room where roomNumber = ?", Integer.class,roomNumber);
        return count != null && count > 0;
    }

    public boolean roomIsAvalaible(Integer roomNumber){
        Integer count =  jdbcTemplate.queryForObject("select count(*) from room where roomNumber = ? and avalaible = true", Integer.class,roomNumber);
        return count != null && count > 0;
    }

}
