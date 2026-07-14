package com.sudarshan.kumar.sb_poc_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="shipment_address")
@NoArgsConstructor
@Getter
public class ShipmentAddress extends BaseAddress {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "shipmentAddress")
    private Shipment shipment;

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
