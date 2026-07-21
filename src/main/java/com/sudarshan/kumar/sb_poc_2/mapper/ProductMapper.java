package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.Mapper;

import com.sudarshan.kumar.sb_poc_2.dto.ProductDto;
import com.sudarshan.kumar.sb_poc_2.models.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {  
    
    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);
}
