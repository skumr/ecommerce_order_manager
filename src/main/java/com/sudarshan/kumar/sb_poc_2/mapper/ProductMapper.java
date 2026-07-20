package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sudarshan.kumar.sb_poc_2.dto.ProductDto;
import com.sudarshan.kumar.sb_poc_2.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {  
    
    @Mapping(target = "product", ignore = true)
    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);
}
