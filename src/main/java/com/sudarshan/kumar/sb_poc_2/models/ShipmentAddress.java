package com.sudarshan.kumar.sb_poc_2.models;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import jakarta.persistence.OneToOne;

@Entity
@Table(name="shipment_address")
public class ShipmentAddress extends BaseAddress {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "shipmentAddress")
    private Shipment shipment;

    public Long getId() {
        return this.id;
    }

    public Shipment getShipment() {
        return this.shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
