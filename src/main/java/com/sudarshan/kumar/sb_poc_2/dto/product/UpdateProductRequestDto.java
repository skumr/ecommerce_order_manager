package com.sudarshan.kumar.sb_poc_2.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductRequestDto {

    private String name;
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;
}
