package br.com.ada.reservala.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class ClientDTORequest {
    @NotBlank(message = "name cannot be null")
    private String name;
    @NotNull(message = "age cannot be null")
    @Min(value = 18, message = "age should not be less than 18")
    private int age;
}
