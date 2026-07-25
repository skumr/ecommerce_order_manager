package com.sudarshan.kumar.sb_poc_2.dto.supplier;

import java.util.List;

import com.sudarshan.kumar.sb_poc_2.dto.AddressResponseDto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSupplierRequestDto {

    private String name;
    @Email
    private String email;
    private String accountManager;
    private String pointOfContact;
    private List<AddressResponseDto> addresses;
}
