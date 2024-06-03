package br.com.ada.reservala.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Room {

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
