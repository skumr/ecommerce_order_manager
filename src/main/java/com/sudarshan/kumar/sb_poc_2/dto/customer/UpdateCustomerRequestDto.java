package com.sudarshan.kumar.sb_poc_2.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCustomerRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;
}
