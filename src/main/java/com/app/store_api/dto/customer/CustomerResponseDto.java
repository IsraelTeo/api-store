package com.app.store_api.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CustomerResponseDto(
        @JsonProperty("customer_id")
        UUID customerId,
        String name,
        @JsonProperty("last_name")
        String lastName,
        String dni,
        @JsonProperty("creation_date")
        LocalDate creationDate
) {
}
