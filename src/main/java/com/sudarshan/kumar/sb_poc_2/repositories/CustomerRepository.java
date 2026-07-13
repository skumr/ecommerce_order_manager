package com.sudarshan.kumar.sb_poc_2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);

}
