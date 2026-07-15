package com.sudarshan.kumar.sb_poc_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shipment_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShipmentAddress extends BaseAddress {

    @NotNull
    @OneToOne(mappedBy = "shipmentAddress")
    private Shipment shipment;
}
