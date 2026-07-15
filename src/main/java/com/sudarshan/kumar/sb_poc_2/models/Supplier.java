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
@Table(name="suppliers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Supplier extends BaseEntity {

    @NotBlank
    @ToString.Include
    private String name;

    @NotBlank
    @ToString.Include
    @Email
    private String email;

    @NotBlank
    @ToString.Include
    private String accountManager;

    @NotBlank
    @ToString.Include
    private String pointofContact;

    @NotNull
    @ToString.Include
    @OneToMany(mappedBy = "supplier",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<SupplierAddress> addresses = new ArrayList<>();

    @NotNull
    @ToString.Include
    @OneToMany(mappedBy = "supplier",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
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