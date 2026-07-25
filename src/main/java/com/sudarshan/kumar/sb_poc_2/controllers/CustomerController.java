package com.sudarshan.kumar.sb_poc_2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.kumar.sb_poc_2.dto.customer.CreateCustomerRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.customer.CustomerResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.customer.UpdateCustomerRequestDto;
import com.sudarshan.kumar.sb_poc_2.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(
            @PathVariable Long id
    ) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/search/{name}")
    public List<CustomerResponseDto> getCustomersByName(
            @PathVariable String name
    ) {
        return customerService.getCustomersByName(name);
    }

    @GetMapping("/email")
    public CustomerResponseDto getCustomerByEmail(
            @RequestParam String email
    ) {
        return customerService.getCustomerByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(
            @Valid @RequestBody CreateCustomerRequestDto customerDto
    ) {
        return customerService.createCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequestDto customerDto
    ) {
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(
            @PathVariable Long id
    ) {
        customerService.deleteCustomer(id);
    }
}