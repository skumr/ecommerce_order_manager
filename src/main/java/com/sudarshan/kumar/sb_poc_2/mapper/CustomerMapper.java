package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sudarshan.kumar.sb_poc_2.dto.AddressDto;
import com.sudarshan.kumar.sb_poc_2.dto.CustomerDto;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.CustomerAddress;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "addresses", source = "addresses")
    CustomerDto toDto(Customer customer);

    @Mapping(target = "customer", ignore = true)
    AddressDto toDto(CustomerAddress address);

    @Mapping(target = "addresses", source = "addresses")
    Customer toEntity(CustomerDto customerDto);

    @Mapping(target = "customer", ignore = true)
    CustomerAddress toEntity(AddressDto dto);
}