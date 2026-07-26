package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sudarshan.kumar.sb_poc_2.dto.product.CreateProductRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.product.ProductResponseDto;
import com.sudarshan.kumar.sb_poc_2.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Product toEntity(CreateProductRequestDto dto);
}