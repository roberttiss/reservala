package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Reservation;
import br.com.ada.reservala.exception.*;
import br.com.ada.reservala.repository.ReservationRepository;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final ClientService clientService;

    public ReservationService(ReservationRepository repository, @Lazy RoomService roomService, @Lazy ClientService clientService, @Lazy RoomRepository roomRepository) {
        this.reservationRepository = repository;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
        this.clientService = clientService;
    }

    public Reservation createReservation(Reservation reservation){
        clientService.checkIfClientDoesNotExist(reservation.getIdClient());
        roomService.checkIfRoomDoesNotExists(reservation.getRoomNumber());
        roomService.checkIfRoomDoesNotAvailable(reservation.getRoomNumber());
        checkIfRoomIsAvailableInDateRequested(reservation.getRoomNumber(), reservation);
        checkIfCheckInIsBeforeToday(reservation);
        checkIfCheckoutIsBeforeCheckIn(reservation);

        int id = reservationRepository.getLastInsertedId().orElse(0) + 1;
        reservation.setIdReservation(id);

        checkIfReservationStartsToday(reservation);

        return reservationRepository.createReservation(reservation);
    }

    public List<Reservation> readReservations(){
        return reservationRepository.readReservation();
    }

    public List<Reservation> readReservationsByClientId(Integer idClient){
        return reservationRepository.readReservationByClientId(idClient);
    }

    public Optional<Reservation> readReservationByReservationId(Integer idReservation){
        checkIfReservationDoesNotExist(idReservation);
        return Optional.of(reservationRepository.readReservationByReservationId(idReservation));
    }

    public void deleteReservation(Integer idReservation){
        checkIfReservationDoesNotExist(idReservation);
        reservationRepository.deleteReservation(idReservation);
    }

    public Optional<Reservation> updateReservation(Reservation reservation, Integer idReservation){
        checkIfReservationDoesNotExist(idReservation);
        return Optional.of(reservationRepository.updateReservation(reservation,idReservation));
    }

    private void checkIfReservationDoesNotExist(int idReservation) {
        if (!reservationRepository.existsReservation(idReservation)){
                ExceptionThrower.throwReservationNotFoundException("Reservation with id " + idReservation + " not exist.");
        }
    }

    private void checkIfReservationStartsToday(Reservation reservation){
        if (reservation.getCheckIn().equals(LocalDate.now())){
            roomRepository.setAvailableFalseRoom(reservation.getRoomNumber());
        }
    }

    public void checkIfCheckInIsBeforeToday(Reservation reservation){
        if (reservation.getCheckIn().isBefore(LocalDate.now())){
            ExceptionThrower.throwReservationFailedException("Select a date for reservation from " + LocalDate.now() + ".");
        }
    }

    public void checkIfCheckoutIsBeforeCheckIn(Reservation reservation){
        if (reservation.getCheckOut().isBefore(reservation.getCheckIn())){
            ExceptionThrower.throwReservationFailedException("Check-out date cannot be before check-in date");
        }
    }

    public void checkIfClientHasReservation(Integer idClient){
        if (reservationRepository.clientHasReservation(idClient)){
            ExceptionThrower.throwClientHasReservationException("is not possible to delete a client if there is a reservation assigned to them");
        }
    }

    public void checkIfRoomHasReservation(Integer roomNumber){
        if (reservationRepository.roomHasReservation(roomNumber)){
            ExceptionThrower.throwRoomHasReservationException("is not possible to delete a room if there is a reservation assigned to them");
        }
    }

    private void checkIfRoomIsAvailableInDateRequested(int roomNumber, Reservation reservation) {
            List<LocalDate> datesCheckIn = reservationRepository.getCheckIn(roomNumber);
            List<LocalDate> datesCheckOut = reservationRepository.getCheckOut(roomNumber);
            LocalDate checkOut = reservation.getCheckOut();
                for (LocalDate dateCheckIn: datesCheckIn){
                    for (LocalDate dateCheckOut: datesCheckOut){
                        boolean flagOut = checkOut.isBefore(dateCheckOut) | checkOut.equals(dateCheckOut) && checkOut.isAfter(dateCheckIn);
                            if (flagOut){
                                ExceptionThrower.throwRoomNotAvailableException("Room with number " + roomNumber + " not available in date specific");
                            }
                    }
                }
        }

}


