package com.sudarshan.kumar.sb_poc_2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByNameIgnoreCase(String name);

    Optional<Supplier> findByEmailIgnoreCase(String email);

    Optional<Supplier> findByAccountManagerIgnoreCase(String accountManager);

    Optional<Supplier> findByPointOfContactIgnoreCase(String pointOfContact);
    
    List<Supplier> findByProducts_NameIgnoreCase(String productName);
    
    boolean existsByNameIgnoreCase(String name);

    boolean existsByEmailIgnoreCase(String email);
}
