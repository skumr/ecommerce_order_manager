package com.sudarshan.kumar.sb_poc_2.models;

import java.math.BigDecimal;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "item_orders")
@Data
@ToString(onlyExplicitlyIncluded=true)
@EqualsAndHashCode(callSuper=true)
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotBlank
    private String productName;

    @NotNull
    @Min(0)
    private int quantity;

    @NotNull
    @Min(0)
    private BigDecimal unitPrice;
}