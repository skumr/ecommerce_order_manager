package com.sudarshan.kumar.sb_poc_2.models;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipment_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class ShipmentAddress extends BaseAddress {

    @NotNull
    @OneToOne(
        mappedBy = "shipmentAddress",
        fetch = FetchType.LAZY
    )
    private Shipment shipment;
}