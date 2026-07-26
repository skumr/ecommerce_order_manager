package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.product.ProductResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.supplier.CreateSupplierRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.supplier.SupplierResponseDto;
import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;
import com.sudarshan.kumar.sb_poc_2.models.SupplierAddress;

@Mapper(componentModel="spring")
public interface SupplierMapper {

    SupplierResponseDto toDto(Supplier supplier);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "products", ignore = true)
    Supplier toEntity(CreateSupplierRequestDto dto);

    AddressResponseDto toDto(SupplierAddress address);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    SupplierAddress toEntity(AddressResponseDto dto);

    @Mapping(target = "supplier", ignore = true)
    ProductResponseDto toDto(Product product);

    Product toEntity(Product product);

    @AfterMapping
    default void linkAddresses(@MappingTarget Supplier supplier) {
        supplier.getAddresses()
                .forEach(address -> address.setSupplier(supplier));
    }
    
    @AfterMapping
    default void linkProduct(@MappingTarget Supplier supplier) {
        supplier.getProducts()
                .forEach(product -> product.setSupplier(supplier));
    }
}
