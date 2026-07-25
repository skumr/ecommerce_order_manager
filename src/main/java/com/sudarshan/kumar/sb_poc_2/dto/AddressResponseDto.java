package com.sudarshan.kumar.sb_poc_2.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressResponseDto {

    private Long id;
    private String unit;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;    
}
