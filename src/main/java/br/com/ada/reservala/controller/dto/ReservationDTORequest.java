package br.com.ada.reservala.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ReservationDTORequest {
    private int idClient;
    private int roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
