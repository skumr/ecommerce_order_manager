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

    AddressResponseDto toDto(CustomerAddress address);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "customer", ignore = true)
    CustomerAddress toEntity(AddressResponseDto dto);

    CustomerResponseDto toDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CreateCustomerRequestDto customerDto);

    @AfterMapping
    default void linkAddresses(@MappingTarget Customer customer) {
        if (customer.getAddresses() != null) {
            customer.getAddresses()
                    .forEach(address -> address.setCustomer(customer));
        }
    }
}