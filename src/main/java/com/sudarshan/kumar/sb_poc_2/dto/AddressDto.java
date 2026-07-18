package com.sudarshan.kumar.sb_poc_2.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AddressDto {

    private Long id;
    private String unit;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    
}
