package com.app.store_api.dto.customer;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CustomerResponseDto(
        UUID customerId,
        String name,
        String lastName,
        String dni,
        LocalDate creationDate
) {
}
