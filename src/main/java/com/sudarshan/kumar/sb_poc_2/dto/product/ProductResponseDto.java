package com.sudarshan.kumar.sb_poc_2.dto.product;

import java.math.BigDecimal;

import com.sudarshan.kumar.sb_poc_2.dto.supplier.SupplierResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private SupplierResponseDto supplier;
    private BigDecimal price;
    private Integer quantity;
}
