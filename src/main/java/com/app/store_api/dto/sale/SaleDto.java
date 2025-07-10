package com.app.store_api.dto.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record SaleDto(

        @JsonProperty("customer_id")
        @NotNull(message = "Customer ID must not be null")
        UUID customerId,

        @JsonProperty("products_id")
        @NotEmpty(message = "Sale must include at least one product")
        List<@NotNull UUID> productsId
) {
}
