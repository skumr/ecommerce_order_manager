package com.sudarshan.kumar.sb_poc_2.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateSupplierRequestDto {

    private Long supplierId;
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Email is required")
    @Email
    private String email;
    @NotBlank(message="Account manager is reqiured")
    private String accountManager;
    private String pointOfContact;
}
