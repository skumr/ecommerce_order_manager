package com.sudarshan.kumar.sb_poc_2.dto.product;

import java.math.BigDecimal;

import com.sudarshan.kumar.sb_poc_2.dto.supplier.SupplierReferenceDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRequestDto {
    
    @NotBlank(message="Product name is required")
    private String name;
    @NotBlank(message="Supplier is required")    
    private SupplierReferenceDto supplier;
    private Long supplierId;
    @NotNull(message="Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;
    @NotNull(message="Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;
}
