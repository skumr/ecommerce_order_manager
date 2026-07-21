package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sudarshan.kumar.sb_poc_2.dto.AddressDto;
import com.sudarshan.kumar.sb_poc_2.dto.ProductDto;
import com.sudarshan.kumar.sb_poc_2.dto.SupplierDto;
import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;
import com.sudarshan.kumar.sb_poc_2.models.SupplierAddress;

@Mapper(componentModel="spring")
public interface SupplierMapper {

    SupplierDto toDto(Supplier supplier);

    Supplier toEntity(SupplierDto dto);

    @Mapping(target = "supplier", ignore = true)
    AddressDto toDto(SupplierAddress address);

    SupplierAddress toEntity(AddressDto dto);

    @Mapping(target = "supplier", ignore = true)
    ProductDto toDto(Product product);

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
