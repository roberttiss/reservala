package br.com.ada.reservala.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ReservationDTOResponse {
    private int idClient;
    private int idReservation;
    private int roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
