package br.com.ada.reservala.controller.dto;

import br.com.ada.reservala.domain.Reservation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDTOResponse {
    private int idClient;
    private String name;
    private int age;
}
