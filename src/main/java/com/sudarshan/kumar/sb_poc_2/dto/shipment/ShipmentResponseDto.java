package com.sudarshan.kumar.sb_poc_2.dto.shipment;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.order.OrderResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShipmentResponseDto {
    
    private Long id;
    private OrderResponseDto order;
    private AddressResponseDto shipmentAddress;
    private String shipmentStatus;
}
