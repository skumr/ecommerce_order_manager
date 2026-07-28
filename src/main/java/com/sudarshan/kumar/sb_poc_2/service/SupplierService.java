package com.sudarshan.kumar.sb_poc_2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.dto.supplier.CreateSupplierRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.supplier.SupplierResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.supplier.UpdateSupplierRequestDto;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.mapper.SupplierMapper;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;
import com.sudarshan.kumar.sb_poc_2.repositories.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class SupplierService {
    
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public List<SupplierResponseDto> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .toList();
    }

    public SupplierResponseDto getSupplierById(Long id) {
        return supplierMapper.toDto(getSupplier(id));
    }

    public List<SupplierResponseDto> getSuppliersByName(String name) {
        List<Supplier> suppliers = supplierRepository.findByNameIgnoreCase(name);

        if (suppliers.isEmpty()) {
            throw new ResourceNotFoundException("Supplier", name);
        }

        return suppliers.stream().map(supplierMapper::toDto).toList();
    }

    public List<SupplierResponseDto> getSupplierByProductName(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new ResourceNotFoundException("Supplier", productName);
        }

        return supplierRepository.findByProducts_NameIgnoreCase(productName)
                                    .stream()
                                    .map(supplierMapper::toDto)
                                    .toList();
    }

    @Transactional
    public SupplierResponseDto createSupplier(CreateSupplierRequestDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);

        return supplierMapper.toDto(supplier);
    }

    @Transactional
    public SupplierResponseDto updateSupplier(Long id, UpdateSupplierRequestDto updatedSupplierDto) {
        Supplier currSupplier = getSupplier(id);

        currSupplier.setName(updatedSupplierDto.getName());
        currSupplier.setEmail(updatedSupplierDto.getEmail());

        return supplierMapper.toDto(currSupplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        supplierRepository.delete(getSupplier(id));
    }

    private Supplier getSupplier(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", id));
    }
}
