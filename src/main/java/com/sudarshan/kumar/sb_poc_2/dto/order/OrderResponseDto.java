package com.sudarshan.kumar.sb_poc_2.dto.order;

import java.util.List;

import com.sudarshan.kumar.sb_poc_2.dto.customer.CustomerResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.shipment.ShipmentResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private List<OrderItemResponseDto> orderItems;
    private CustomerResponseDto customer;
    private ShipmentResponseDto shipment;
}
