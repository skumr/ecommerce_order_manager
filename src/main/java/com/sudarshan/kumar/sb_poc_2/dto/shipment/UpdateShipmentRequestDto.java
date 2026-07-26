package com.sudarshan.kumar.sb_poc_2.dto.shipment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateShipmentRequestDto {

    private String trackingNumber;
    private String shipmentStatus;
}
