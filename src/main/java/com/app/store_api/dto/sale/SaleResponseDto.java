package com.app.store_api.dto.sale;

import com.app.store_api.dto.customer.CustomerResponseDto;
import com.app.store_api.dto.product.ProductResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record SaleResponseDto(

        @JsonProperty("sale_id")
        UUID saleId,

        @JsonProperty("creation_date")
        LocalDate creationDate,

        @JsonProperty("total_amount")
        BigDecimal totalAmount,

        @JsonProperty("customer")
        CustomerResponseDto customerResponseDto,

        @JsonProperty("products")
        List<ProductResponseDto> productsResponseDto
) {
}