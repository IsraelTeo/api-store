package com.app.store_api.dto.sale;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record SaleDto(
        @NotNull(message = "Customer ID must not be null")
        UUID customerId,

        @NotEmpty(message = "Sale must include at least one product")
        List<@NotNull UUID> productsId
) {
}
