package com.sudarshan.kumar.sb_poc_2.service;

import org.springframework.stereotype.Service;

import com.sudarshan.kumar.sb_poc_2.repositories.CustomerRepository;

import com.sudarshan.kumar.sb_poc_2.models.Customer;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id);
                                    .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }
    
}
