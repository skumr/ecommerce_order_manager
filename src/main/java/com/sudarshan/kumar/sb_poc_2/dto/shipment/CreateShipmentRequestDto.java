package com.sudarshan.kumar.sb_poc_2.dto.shipment;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.order.OrderReferenceDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShipmentRequestDto {

    private OrderReferenceDto order;
    private AddressResponseDto shipmentAddress;
    @NotBlank(message="Tracking number is required")
    private String trackingNumber;
    @NotBlank(message="Shipment status is required ")
    private String shipmentStatus;
    
}
