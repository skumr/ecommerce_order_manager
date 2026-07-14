package com.sudarshan.kumar.sb_poc_2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByName(String name);

    Optional<Supplier> findByEmail(String email);

    Optional<Supplier> findByAccountManager(String accountManager);

    Optional<Supplier> findByPointOfContact(String pointOfContact);
}
