package com.sudarshan.kumar.sb_poc_2.service;

import com.sudarshan.kumar.sb_poc_2.repositories.SupplierRepository;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
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

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier currSupplier = getSupplierById(id);

        currSupplier.setName(updatedSupplier.getName());
        currSupplier.setEmail(updatedSupplier.getEmail());

        return supplierRepository.save(currSupplier);
    }

    public void deleteOrder(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }


}
