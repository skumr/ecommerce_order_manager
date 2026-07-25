package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.customer.CreateCustomerRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.customer.CustomerResponseDto;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.CustomerAddress;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // This is mapping a CustomerAddress object to the addressDto -- dont link the customer field
    @Mapping(target = "customer", ignore = true)
    AddressResponseDto toDto(CustomerAddress address);

    CustomerAddress toEntity(AddressResponseDto dto);

    CustomerResponseDto toDto(Customer customer);

    Customer toEntity(CreateCustomerRequestDto customerDto);

    @AfterMapping
    default void linkAddresses(@MappingTarget Customer customer) {
        customer.getAddresses()
                .forEach(address -> address.setCustomer(customer));
    }
}