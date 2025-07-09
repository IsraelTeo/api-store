package com.app.store_api.service;

import com.app.store_api.dto.product.ProductDto;
import com.app.store_api.dto.criteria.SearchProductCriteriaDto;
import com.app.store_api.dto.product.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductResponseDto getById(UUID id);

    ProductResponseDto save (ProductDto productDto);

    ProductResponseDto update (UUID id, ProductDto productDto);

    void deleteById(UUID id);

    List<ProductResponseDto> getProducts(SearchProductCriteriaDto criteriaDto);
}
