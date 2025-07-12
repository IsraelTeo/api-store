package com.app.store_api.service;

import com.app.store_api.dto.product.ProductDto;
import com.app.store_api.dto.criteria.SearchProductCriteriaDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductDto getById(UUID id);

    ProductDto save (ProductDto productDto);

    ProductDto update (UUID id, ProductDto productDto);

    void deleteById(UUID id);

    List<ProductDto> getProducts(SearchProductCriteriaDto criteriaDto);
}
