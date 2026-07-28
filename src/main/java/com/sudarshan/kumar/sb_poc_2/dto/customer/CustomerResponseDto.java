package com.sudarshan.kumar.sb_poc_2.dto.customer;

import java.util.List;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerResponseDto {

    @NotBlank(message="Customer name is required")
    private String name;
    @NotBlank(message="Email cannot be blank")
    @Email
    private String email;
    @NotBlank(message="Addresses cannot be blank")
    private List<AddressResponseDto> addresses;
}
