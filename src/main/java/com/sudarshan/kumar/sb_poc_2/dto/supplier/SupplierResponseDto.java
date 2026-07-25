package com.sudarshan.kumar.sb_poc_2.dto.supplier;

import java.util.List;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.product.ProductResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierResponseDto {

    private String name;
    private String email;
    private String accountManager;
    private String pointOfContact;
    private List<AddressResponseDto> addresses;
    private List<ProductResponseDto> products;
}
