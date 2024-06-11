package br.com.ada.reservala.controller.dto;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class RoomDTORequest {
    @NotNull(message = "number of room cannot be null")
    @Min(value = 1,message = "number of room should not be less than 1")
    @Digits(integer = 3, fraction = 0, message = "number of room should be an integer")
    private Integer roomNumber;

    @NotBlank(message = "type cannot be null")
    private String type;

    @NotNull
    @Digits(integer = 3, fraction = 3, message = "price should not have more than 3 integer and 3 fraction digits")
    private BigDecimal price;

    @NotNull(message = "available cannot be null")
    private Boolean available;
}