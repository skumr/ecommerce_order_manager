package com.sudarshan.kumar.sb_poc_2.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class OrderItemDto extends BaseDto {

    private OrderDto order;
    private ProductDto product;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
}
