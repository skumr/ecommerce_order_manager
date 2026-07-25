package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.Mapper;

import com.sudarshan.kumar.sb_poc_2.dto.product.CreateProductRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.product.ProductResponseDto;
import com.sudarshan.kumar.sb_poc_2.models.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {  
    
    ProductResponseDto toDto(Product product);

    Product toEntity(CreateProductRequestDto dto);
}
