package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Reservation;
import br.com.ada.reservala.exception.*;
import br.com.ada.reservala.repository.ReservationRepository;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private RoomService roomService;
    private ClientService clientService;

    public ReservationService(ReservationRepository repository, RoomService roomService, ClientService clientService, RoomRepository roomRepository) {
        this.reservationRepository = repository;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
        this.clientService = clientService;
    }

    public Reservation createReservation(Reservation reservation){
        clientService.existsClient(reservation.getIdClient(), new ClientNotFoundException("Client with id " + reservation.getIdClient() + " not found."));
        roomService.roomExists(reservation.getRoomNumber(),new RoomNotFoundException("Room with number " + reservation.getRoomNumber() + " not found."));
        roomService.roomIsAvalaible(reservation.getRoomNumber(),new RoomNotAvailableException("Room with number " + reservation.getRoomNumber() + " not found."));
        verifyRoomIsAvailableInDateRequested(reservation.getRoomNumber(), reservation);
        verifyDateIsBeforeNow(reservation);

        int id = reservationRepository.getLastInsertedId().orElse(0) + 1;
        reservation.setIdReservation(id);

        verifyDateIsNow(reservation);

        return reservationRepository.createReservation(reservation);
    }

    public List<Reservation> readReservations(){
        return reservationRepository.readReservation();
    }

    public List<Reservation> readReservationsByClientId(Integer idClient){
        return reservationRepository.readReservationByClientId(idClient);
    }

    public Optional<Reservation> readReservationByReservationId(Integer idReservation){
        verifyReservationExists(idReservation);
        return Optional.of(reservationRepository.readReservationByReservationId(idReservation));
    }

    public void deleteReservation(Integer idReservation){
        verifyReservationExists(idReservation);
        reservationRepository.deleteReservation(idReservation);
    }

    public Optional<Reservation> updateReservation(Reservation reservation, Integer idReservation){
        verifyReservationExists(idReservation);
        return Optional.of(reservationRepository.updateReservation(reservation,idReservation));
    }

    private void verifyReservationExists(int idReservartion) {
        if (!reservationRepository.existsReservation(idReservartion)){
            throw new ReservationNotFoundException("Reservation with id " + idReservartion + " not found.");
        }
    }

    private void verifyDateIsNow(Reservation reservation){
        if (reservation.getCheckIn().equals(LocalDate.now())){
            roomRepository.setAvailableFalseRoom(reservation.getRoomNumber());
        }
    }

    private void verifyDateIsBeforeNow(Reservation reservation){
        if (reservation.getCheckIn().isBefore(LocalDate.now())){
            throw new RoomNotAvailableException("Select a date for reservation from " + LocalDate.now() + ".");
        }
    }

    private void verifyRoomIsAvailableInDateRequested(int roomNumber, Reservation reservation) {
            List<LocalDate> datesCheckIn = reservationRepository.getCheckIn(roomNumber);
            List<LocalDate> datesCheckOut = reservationRepository.getCheckOut(roomNumber);
            LocalDate checkOut = reservation.getCheckOut();
                for (LocalDate dateCheckIn: datesCheckIn){
                    for (LocalDate dateCheckOut: datesCheckOut){
                        boolean flagOut = checkOut.isBefore(dateCheckOut) | checkOut.equals(dateCheckOut) && checkOut.isAfter(dateCheckIn);
                            if (flagOut){
                                throw new RoomNotAvailableException("Room with number " + roomNumber + " not available in date specific");
                            }
                    }
                }
        }

}


