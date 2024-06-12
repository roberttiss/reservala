package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Reservation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reservation createReservation(Reservation reservation){
        String createSQL = "insert into reservation(id, idClient, roomNumber, checkIn, checkOut) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                createSQL,
                reservation.getIdReservation(),
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

        String readSQL = "select * from reservation";
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

    public Reservation readReservationByReservationId(int idReservation){
    RowMapper<Reservation> rowMapper = ((rs, rowNum) -> new Reservation(
            rs.getInt("id"),
            rs.getInt("idClient"),
            rs.getInt("roomNumber"),
            rs.getDate("checkIn").toLocalDate(),
            rs.getDate("checkOut").toLocalDate()
    ));

    String sql = "select * from reservation where id = ?";

    return jdbcTemplate.queryForObject(sql, rowMapper, idReservation);
}

    public Reservation updateReservation(Reservation reservation, Integer idReservation){
        String updateSQL = "update reservation set  roomNumber = ?, checkIn = ?, checkOut = ? where id = ?";
        jdbcTemplate.update(
                updateSQL,
                reservation.getRoomNumber(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                idReservation
        );
        return readReservationByReservationId(idReservation);
    }

    public void deleteReservation(Integer idReservation){
        String deleteSQL = "delete from reservation where id = ?";
        jdbcTemplate.update(
                deleteSQL,idReservation
        );
    }

    public Optional<Integer> getLastInsertedId(){
    try {
        Integer lastId = jdbcTemplate.queryForObject("SELECT max(id) FROM reservation", Integer.class);
            return Optional.ofNullable(lastId);
                } catch (EmptyResultDataAccessException e) {
                    return Optional.empty();
        }
    }

    public Boolean existsReservation(Integer idReservation){
        Integer count = jdbcTemplate.queryForObject("select count (*) from reservation where id = ?", Integer.class,idReservation);
        return count != null && count > 0;
    }

    public List<LocalDate> getCheckIn(Integer roomNumber){
        String sql = "select * from reservation where roomNumber = ?";
        RowMapper<LocalDate> rowMapper = (rs, rowNum) -> rs.getDate("checkIn").toLocalDate();

        return jdbcTemplate.query(sql,rowMapper,roomNumber);
    }

    public List<LocalDate> getCheckOut(Integer roomNumber){
        String sql = "select * from reservation where roomNumber = ?";
        RowMapper<LocalDate> rowMapper = (rs, rowNum) -> rs.getDate("checkOut").toLocalDate();

        return jdbcTemplate.query(sql,rowMapper,roomNumber);
    }




}
