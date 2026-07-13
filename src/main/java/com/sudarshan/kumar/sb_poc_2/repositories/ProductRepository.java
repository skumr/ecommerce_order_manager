package com.sudarshan.kumar.sb_poc_2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    Optional<Product> findBySupplier(Supplier supplier);

    Optional<Product> findByPrice(double price);

    Optional<Product> findByQuantity(int quantity);
}
