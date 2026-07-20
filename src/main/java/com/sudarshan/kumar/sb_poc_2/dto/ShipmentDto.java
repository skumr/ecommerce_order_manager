package com.sudarshan.kumar.sb_poc_2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class ShipmentDto extends BaseDto {

    private OrderDto order;
    private AddressDto address;
    private String trackingNumber;
    private String shipmentStatus;
    
}
