package br.com.ada.reservala.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RoomDTORequest {

    @NotNull
    @Min(1)
    private Integer roomNumber;

    @NotBlank
    private String type;

    @NotNull
    @Min(3)
    private Integer price;

    @NotNull
    private Boolean avalaible;
}