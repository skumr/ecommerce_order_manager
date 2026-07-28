package com.sudarshan.kumar.sb_poc_2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sudarshan.kumar.sb_poc_2.dto.order.CreateOrderRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.order.OrderResponseDto;
import com.sudarshan.kumar.sb_poc_2.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "shipment", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(CreateOrderRequestDto dto);
}
