package com.sudarshan.kumar.sb_poc_2.dto.supplier;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierReferenceDto {
    @NotNull
    @Positive
    private Long id;
}
