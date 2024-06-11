package br.com.ada.reservala.controller.mapper;

import br.com.ada.reservala.controller.dto.ReservationDTORequest;
import br.com.ada.reservala.controller.dto.ReservationDTOResponse;
import br.com.ada.reservala.domain.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationDTORequest reservationDTORequest){
        return Reservation.builder()
                .idClient(reservationDTORequest.getIdClient())
                .roomNumber(reservationDTORequest.getRoomNumber())
                .checkIn(reservationDTORequest.getCheckIn())
                .checkOut(reservationDTORequest.getCheckOut())
                .build();
    }

    public List<ReservationDTOResponse> todto(List<Reservation> reservations){
        return reservations.stream()
                .map(this::todto)
                .collect(Collectors.toList());
    }

    public ReservationDTOResponse todto(Reservation reservation){
        return ReservationDTOResponse.builder()
                .idReservation(reservation.getIdReservation())
                .idClient(reservation.getIdClient())
                .roomNumber(reservation.getRoomNumber())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .build();
    }
}
