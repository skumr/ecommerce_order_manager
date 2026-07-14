package com.sudarshan.kumar.sb_poc_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_shipments")
@NoArgsConstructor
@Getter
public class Shipment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @OneToOne
    @JoinColumn(name = "shipment_address_id")
    private ShipmentAddress shipmentAddress;

    private String trackingNumber;

    private String shipmentStatus;

    public Shipment(Order order, ShipmentAddress shipmentAddress, String trackingNumber, String shipmentStatus) {
        this.order = order;
        this.shipmentAddress = shipmentAddress;
        this.shipmentStatus = shipmentStatus;
        this.trackingNumber = trackingNumber;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setShipmentAddress(ShipmentAddress shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public void setShipmentStatus(String status) {
        this.shipmentStatus = status;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
