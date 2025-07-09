package com.app.store_api.dto.product;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponseDto(
        UUID productId,
        String name,
        String tradeMark,
        BigDecimal price
) {
}