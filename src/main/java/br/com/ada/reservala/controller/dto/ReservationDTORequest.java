package br.com.ada.reservala.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ReservationDTORequest {
    @NotNull(message = "id of client cannot be null and must exist")
    private int idClient;

    @NotNull(message = "number of room cannot be null")
    @Min(value = 1,message = "number of room should not be less than 1")
    @Digits(integer = 3, fraction = 0, message = "number of room should be an integer")
    private Integer roomNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkOut;
}
