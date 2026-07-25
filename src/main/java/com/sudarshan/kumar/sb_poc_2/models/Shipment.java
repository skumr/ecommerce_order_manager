package com.sudarshan.kumar.sb_poc_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "order_shipments")
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded=true)
@EqualsAndHashCode(callSuper=true)
public class Shipment extends BaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_address_id")
    private ShipmentAddress shipmentAddress;
    @NotBlank
    private String trackingNumber;
    @NotBlank
    private String shipmentStatus;
}