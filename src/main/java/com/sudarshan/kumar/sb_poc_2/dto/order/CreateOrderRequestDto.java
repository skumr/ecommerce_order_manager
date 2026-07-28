package com.sudarshan.kumar.sb_poc_2.dto.order;

import com.sudarshan.kumar.sb_poc_2.dto.customer.CustomerResponseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class CreateOrderRequestDto {

    private CustomerResponseDto customer;
    
}
