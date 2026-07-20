package com.sudarshan.kumar.sb_poc_2.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameIgnoreCase(String name);

    List<Product> findBySupplier(Supplier supplier);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByQuantityLessThan(int quantity);

    List<Product> findByQuantityGreaterThan(int quantity);

    List<Product> findBySupplierAndQuantityGreaterThan(
            Supplier supplier,
            int quantity
    );

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByNameStartingWithIgnoreCase(String prefix);

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByOrderByQuantityDesc();

    boolean existsByNameIgnoreCase(String name);
}
