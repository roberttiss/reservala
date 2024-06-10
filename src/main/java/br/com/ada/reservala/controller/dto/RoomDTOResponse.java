package br.com.ada.reservala.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class RoomDTOResponse {
    private Integer roomNumber;
    private String type;
    private BigDecimal price;
    private Boolean avalaible;

}
