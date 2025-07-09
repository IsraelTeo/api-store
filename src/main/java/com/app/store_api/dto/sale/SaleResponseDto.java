package com.app.store_api.dto.sale;

import com.app.store_api.dto.customer.CustomerResponseDto;
import com.app.store_api.dto.product.ProductResponseDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record SaleResponseDto(
        UUID saleId,
        LocalDate creationDate,
        BigDecimal totalAmount,
        CustomerResponseDto customerResponseDto,
        List<ProductResponseDto> productsResponseDto
) {
}