package com.sudarshan.kumar.sb_poc_2.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Customer extends BaseEntity {

    @NotBlank
    @ToString.Include
    private String name;

    @NotBlank
    @Email
    @ToString.Include
    private String email;

    @NotNull
    @ToString.Include
    @OneToMany(
        mappedBy="customer", 
        cascade=CascadeType.ALL, 
        orphanRemoval=true
    )
    private List<CustomerAddress> addresses = new ArrayList<>();

    @ToString.Include
    @OneToMany(
        mappedBy="customer",
        cascade=CascadeType.ALL,
        orphanRemoval=true
    )
    private List<Order> orders = new ArrayList<>();

    public void addAddress(CustomerAddress address) {
        addresses.add(address);
        address.setCustomer(this);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }
}