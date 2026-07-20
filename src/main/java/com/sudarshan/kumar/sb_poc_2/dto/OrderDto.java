package com.sudarshan.kumar.sb_poc_2.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class OrderDto extends BaseDto {

    private List<OrderItemDto> orderItems;
    private CustomerDto customer;
    private ShipmentDto shipmentDto;
}
