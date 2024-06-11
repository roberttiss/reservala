package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Reservation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String createSQL = "insert into reservation(id, idClient, roomNumber, checkIn, checkOut) values (?, ?, ?, ?, ?)";
    private final String readSQL = "select * from reservation";
    private final String updateSQL = "update reservation set  roomNumber = ?, checkIn = ?, checkOut = ? where id = ?";
    private final String deleteSQL = "delete from reservation where id = ?";

    public ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reservation createReservation(Reservation reservation){
        int id;
        if (getLastInsertedId().isEmpty()){
            id = 1;
        } else {
            id = getLastInsertedId().get() + 1;
        }
        jdbcTemplate.update(
                createSQL,
                id,
                reservation.getIdClient(),
                reservation.getRoomNumber(),
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );
        reservation.setIdReservation(id);
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
        jdbcTemplate.update(
                deleteSQL,idReservation
        );
    }

    public LocalDate getCheckouDate(int roomNumber){
        return jdbcTemplate.queryForObject("select checkOut from reservation where roomNumber = ? order by checkOut desc limit 1", LocalDate.class,roomNumber);
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


}
