package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Reservation;
import br.com.ada.reservala.exception.ClientNotFoundException;
import br.com.ada.reservala.exception.ReservationNotFoundException;
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

    public ReservationService(ReservationRepository repository) {
        this.reservationRepository = repository;
    }

    public Reservation createReservation(Reservation reservation){
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

    public void deleteReservation(Integer idReservation){
        if (!reservationRepository.existsReservation(idReservation)){
            throw new ReservationNotFoundException("Reservation with id " + idReservation+ " not found.");
        }
        reservationRepository.deleteReservation(idReservation);
    }

    public Optional<Reservation> updateReservation(Reservation reservation, Integer idClient){
        if (!reservationRepository.existsReservation(reservation.getIdReservation())){
            throw new ReservationNotFoundException("Reservation with id " + reservation.getIdReservation() + " not found.");
        }
        return Optional.of(reservationRepository.updateReservation(reservation,idClient));
    }
}
