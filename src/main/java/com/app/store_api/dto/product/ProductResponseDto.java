package com.app.store_api.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponseDto(
        @JsonProperty("product_id")
        UUID productId,
        String name,
        @JsonProperty("trade_mark")
        String tradeMark,
        BigDecimal price
) {
}