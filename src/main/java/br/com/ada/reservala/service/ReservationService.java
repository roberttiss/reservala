package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Reservation;
import br.com.ada.reservala.exception.ClientNotFoundException;
import br.com.ada.reservala.exception.ReservationNotFoundException;
import br.com.ada.reservala.exception.RoomNotFoundException;
import br.com.ada.reservala.repository.ClientRepository;
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
    private ClientRepository clientRepository;

    public ReservationService(ReservationRepository repository, ClientRepository clientRepository, RoomRepository roomRepository) {
        this.reservationRepository = repository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;

    }

    public Reservation createReservation(Reservation reservation){
        if (!clientRepository.existsClient(reservation.getIdClient())){
            throw new ClientNotFoundException("Client with id " + reservation.getIdClient() + " not found.");
        }
        if (!roomRepository.roomExists(reservation.getRoomNumber())){
                    throw new RoomNotFoundException("Room with number " + reservation.getRoomNumber() + " not found.");
        }
        return reservationRepository.createReservation(reservation);
    }

    public List<Reservation> readReservations(){
        return reservationRepository.readReservation();
    }

    public List<Reservation> readReservationsByClientId(Integer idClient){
        if (!clientRepository.existsClient(idClient)){
            throw new ClientNotFoundException("Client with id " + idClient + " not found.");
        }
        return reservationRepository.readReservationByClientId(idClient);
    }

    public Optional<Reservation> readReservationByReservationId(Integer idReservation){
        if (!reservationRepository.existsReservation(idReservation)){
            throw new ReservationNotFoundException("Reservation with id " + idReservation + " not found.");
        }
        return Optional.of(reservationRepository.readReservationByReservationId(idReservation));
    }

    public void deleteReservation(Integer idReservation){
        if (!reservationRepository.existsReservation(idReservation)){
            throw new ReservationNotFoundException("Reservation with id " + idReservation+ " not found.");
        }
        reservationRepository.deleteReservation(idReservation);
    }

    public Optional<Reservation> updateReservation(Reservation reservation, Integer idReservation){
        if (!reservationRepository.existsReservation(reservation.getIdReservation())){
            throw new ReservationNotFoundException("Reservation with id " + reservation.getIdReservation() + " not found.");
        }
        return Optional.of(reservationRepository.updateReservation(reservation,idReservation));
    }

}
