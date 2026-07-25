package com.sudarshan.kumar.sb_poc_2.dto.order;

import java.util.List;

import com.sudarshan.kumar.sb_poc_2.dto.customer.CustomerResponseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class UpdateOrderRequestDto {

    private List<OrderItemResponseDto> orderItems;
    private CustomerResponseDto customer;
    
}
