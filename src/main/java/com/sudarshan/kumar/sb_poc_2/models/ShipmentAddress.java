package com.sudarshan.kumar.sb_poc_2.models;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "shipment_address")
@Data
@ToString(onlyExplicitlyIncluded=true)
@EqualsAndHashCode(callSuper=true)
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class ShipmentAddress extends BaseAddress {

    @NotNull
    @OneToOne(
        mappedBy = "shipmentAddress",
        fetch = FetchType.LAZY
    )
    private Shipment shipment;
}