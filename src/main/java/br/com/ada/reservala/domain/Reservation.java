package br.com.ada.reservala.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Reservation {
    private int idReservation;
    private int idClient;
    private int roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
