package com.sudarshan.kumar.sb_poc_2.models;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "customer_address")
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded=true)
@EqualsAndHashCode(callSuper=true)
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class CustomerAddress extends BaseAddress {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;
}