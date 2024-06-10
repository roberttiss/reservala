package br.com.ada.reservala.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientDTORequest {
    private String name;
    private int age;
}
