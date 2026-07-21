package com.sudarshan.kumar.sb_poc_2.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sudarshan.kumar.sb_poc_2.dto.SupplierDto;
import com.sudarshan.kumar.sb_poc_2.service.SupplierService;


@Controller
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/allSuppliers")
    public List<SupplierDto> getMethodName() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id")
    public SupplierDto getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping("/name/{name}")
    public List<SupplierDto> getSupplierByName(@PathVariable String name) {
        return supplierService.getSuppliersByName(name);
    }


    
    
    
}
