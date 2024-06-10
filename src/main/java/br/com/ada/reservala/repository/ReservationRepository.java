package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String createSQL = "insert into reservation(id, idRoom, idClient, checkIn, checkOut) values (?, ?, ?, ?, ?)";
    private final String readSQL = "select * from reservation";
    private final String updateSQL = "update reservation set  idRoom = ?, checkIn = ?, checkOut = ? where id = ?";
    private final String deleteSQL = "delete from reservation where id = ?";

    public ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reservation createReservation(Reservation reservation){
        jdbcTemplate.update(
                createSQL,
                reservation.getId(),
                reservation.getIdClient(),
                reservation.getRoomNumber(),
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );
        return reservation;
    }

    public List<Reservation> readReservation(){
        RowMapper<Reservation> rowMapper = ((rs, rowNum) -> new Reservation(
                rs.getInt("id"),
                rs.getInt("idClient"),
                rs.getInt("roomNumber"),
                rs.getDate("checkIn").toLocalDate(),
                rs.getDate("checkOut").toLocalDate()
        ));

        return jdbcTemplate.query(readSQL,rowMapper);
    }

    public List<Reservation> readReservationByClientId(int idClient){
        RowMapper<Reservation> rowMapper = ((rs, rowNum) -> new Reservation(
                rs.getInt("id"),
                rs.getInt("idClient"),
                rs.getInt("roomNumber"),
                rs.getDate("checkIn").toLocalDate(),
                rs.getDate("checkOut").toLocalDate()
        ));

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("select * from reservation where idClient = ?");
                ps.setInt(1,idClient);
                return ps;
            }
        };

        return jdbcTemplate.query(psc,rowMapper);
    }

    public Reservation updateReservation(Reservation reservation, Integer id){
        jdbcTemplate.update(
                updateSQL,
                reservation.getRoomNumber(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                id
        );
        return reservation;
    }

    public void deleteReservation(Integer id){
        jdbcTemplate.update(
                deleteSQL,id
        );
    }

    public LocalDate getCheckouDate(int roomNumber){
        return jdbcTemplate.queryForObject("select checkOut from reservation where roomNumber = ? order by checkOut desc limit 1", LocalDate.class,roomNumber);
    }


}
