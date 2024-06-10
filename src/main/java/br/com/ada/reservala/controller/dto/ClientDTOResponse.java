package br.com.ada.reservala.controller.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClientDTOResponse {
    private int idClient;
    private String name;
    private int age;
}
