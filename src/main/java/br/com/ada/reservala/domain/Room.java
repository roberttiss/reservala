package br.com.ada.reservala.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Room {
    private Integer roomNumber;
    private String type;
    private BigDecimal price;
    private Boolean avalaible;

}
