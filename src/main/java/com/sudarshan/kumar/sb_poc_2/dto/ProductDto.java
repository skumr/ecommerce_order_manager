package com.sudarshan.kumar.sb_poc_2.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class ProductDto extends BaseDto {

    private String name;
    private SupplierDto supplier;
    private BigDecimal price;
    private Integer quantity;
    
}
