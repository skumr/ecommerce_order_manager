package com.sudarshan.kumar.sb_poc_2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.dto.customer.CreateCustomerRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.customer.CustomerResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.customer.UpdateCustomerRequestDto;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.mapper.CustomerMapper;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.repositories.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper
    ) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerResponseDto> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerResponseDto getCustomerById(Long id) {
        return customerMapper.toDto(getCustomer(id));
    }

    public List<CustomerResponseDto> getCustomersByName(String name) {

        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);

        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("Customer", name);
        }

        return customers.stream().map(customerMapper::toDto).toList();
    }

    public CustomerResponseDto getCustomerByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", email));

        return customerMapper.toDto(customer);
    }

    @Transactional
    public CustomerResponseDto createCustomer(CreateCustomerRequestDto customerDto) {

        Customer customer = customerMapper.toEntity(customerDto);

        return customerMapper.toDto(customer);
    }

    @Transactional
    public CustomerResponseDto updateCustomer(
            Long id, 
            UpdateCustomerRequestDto customerDto
    ) {
        Customer customer = getCustomer(id);

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());

        return customerMapper.toDto(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.delete(getCustomer(id));
    }

    private Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }
}
