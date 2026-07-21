package com.sudarshan.kumar.sb_poc_2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.dto.SupplierDto;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.mapper.SupplierMapper;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;
import com.sudarshan.kumar.sb_poc_2.repositories.SupplierRepository;

@Service
@Transactional(readOnly=true)
public class SupplierService {
    
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(
        SupplierRepository supplierRepository,
        SupplierMapper supplierMapper
    ) {
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDto> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .toList();
    }

    public SupplierDto getSupplierById(Long id) {
        return supplierMapper.toDto(getSupplier(id));
    }

    public List<SupplierDto> getSuppliersByName(String name) {
        List<Supplier> suppliers = supplierRepository.findByNameIgnoreCase(name);

        if (suppliers.isEmpty()) {
            throw new ResourceNotFoundException("Supplier", name);
        }

        return suppliers.stream().map(supplierMapper::toDto).toList();
    }

    public List<SupplierDto> getSupplierByProductName(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new ResourceNotFoundException("Supplier", productName);
        }

        return supplierRepository.findByProducts_NameIgnoreCase(productName)
                                    .stream()
                                    .map(supplierMapper::toDto)
                                    .toList();
    }

    @Transactional
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);

        return supplierMapper.toDto(supplier);
    }

    @Transactional
    public SupplierDto updateSupplier(Long id, SupplierDto updatedSupplierDto) {
        Supplier currSupplier = getSupplier(id);

        currSupplier.setName(updatedSupplierDto.getName());
        currSupplier.setEmail(updatedSupplierDto.getEmail());

        return supplierMapper.toDto(currSupplier);
    }

    @Transactional
    public void deleteOrder(Long id) {
        supplierRepository.delete(getSupplier(id));
    }

    private Supplier getSupplier(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", id));
    }


}
