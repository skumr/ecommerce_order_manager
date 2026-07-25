package com.sudarshan.kumar.sb_poc_2.dto.order;

import java.math.BigDecimal;

import com.sudarshan.kumar.sb_poc_2.dto.product.ProductResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemResponseDto {

    private Long id;
    private OrderResponseDto order;
    private ProductResponseDto product;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
}
