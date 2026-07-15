package com.sudarshan.kumar.sb_poc_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_shipments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shipment extends BaseEntity {
    
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @OneToOne
    @JoinColumn(name = "shipment_address_id")
    @NotBlank
    private ShipmentAddress shipmentAddress;

    @NotBlank
    private String trackingNumber;
    
    @NotBlank
    private String shipmentStatus;
}
