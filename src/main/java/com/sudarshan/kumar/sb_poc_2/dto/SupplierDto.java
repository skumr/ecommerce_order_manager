package com.sudarshan.kumar.sb_poc_2.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class SupplierDto extends BaseDto {

    private String name;
    private String email;
    private String accountManager;
    private String pointOfContact;
    private List<AddressDto> addresses;
    private List<ProductDto> products;
}
