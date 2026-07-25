package com.sudarshan.kumar.sb_poc_2.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NotBlank
public class UpdateCustomerAddressRequest {
    
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country; 
}
