package com.sudarshan.kumar.sb_poc_2.service;

import com.sudarshan.kumar.sb_poc_2.repositories.SupplierRepository;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    //Get Supplier by Id

    //Get Supplier by Name

    //Create Supplier

    //Remove Supplier


}
