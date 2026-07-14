package com.sudarshan.kumar.sb_poc_2.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String accountManager;

    @NotBlank
    private String pointofContact;

    @NotNull
    @OneToMany(mappedBy = "supplier",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<SupplierAddress> addresses = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "supplier",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Supplier(String name, String email, String accountManager, String pointOfContact) {
        this.name = name;
        this.email = email;
        this.accountManager = accountManager;
        this.pointofContact = pointOfContact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAddress(SupplierAddress address) {
        addresses.add(address);
        address.setSupplier(this);
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setSupplier(this);
    }

    public void removeAddress(SupplierAddress address) {
        addresses.remove(address);
        address.setSupplier(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setSupplier(this);
    }

    public void setAddresses(List<SupplierAddress> addresses) {
        this.addresses = addresses;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}