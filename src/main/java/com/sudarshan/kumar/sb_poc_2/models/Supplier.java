package com.sudarshan.kumar.sb_poc_2.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "suppliers")
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper=true)
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class Supplier extends BaseEntity {

    @NotBlank
    @ToString.Include
    private String name;

    @NotBlank
    @Email
    @ToString.Include
    private String email;

    @NotBlank
    @ToString.Include
    private String accountManager;

    @NotBlank
    @ToString.Include
    private String pointOfContact;

    @OneToMany(
        mappedBy = "supplier",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @ToString.Include
    private List<SupplierAddress> addresses = new ArrayList<>();

    @OneToMany(
        mappedBy = "supplier",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @ToString.Include
    private List<Product> products = new ArrayList<>();

    public void addAddress(SupplierAddress address) {
        addresses.add(address);
        address.setSupplier(this);
    }

    public void removeAddress(SupplierAddress address) {
        addresses.remove(address);
        address.setSupplier(this);
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setSupplier(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setSupplier(this);
    }
}