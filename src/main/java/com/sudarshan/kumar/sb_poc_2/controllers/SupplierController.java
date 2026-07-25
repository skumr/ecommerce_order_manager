package com.sudarshan.kumar.sb_poc_2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sudarshan.kumar.sb_poc_2.dto.supplier.CreateSupplierRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.supplier.SupplierResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.supplier.UpdateSupplierRequestDto;
import com.sudarshan.kumar.sb_poc_2.service.SupplierService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/allSuppliers")
    public List<SupplierResponseDto> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id")
    public SupplierResponseDto getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping("/name/{name}")
    public List<SupplierResponseDto> getSupplierByName(@PathVariable String name) {
        return supplierService.getSuppliersByName(name);
    }

    @GetMapping("/product/name/{name}")
    public List<SupplierResponseDto> getSupplierByProductName(@PathVariable String productName) {
        return supplierService.getSupplierByProductName(productName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponseDto createSupplier(@Valid @RequestBody CreateSupplierRequestDto supplier) {
        return supplierService.createSupplier(supplier);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponseDto updateSupplier(@PathVariable Long id, @Valid @RequestBody UpdateSupplierRequestDto supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }
}
