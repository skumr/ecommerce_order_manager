package com.sudarshan.kumar.sb_poc_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne(mappedBy = "shipmentAddress")
    private Shipment shipment;
}
