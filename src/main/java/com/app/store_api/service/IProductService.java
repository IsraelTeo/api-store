package com.app.store_api.service;

import com.app.store_api.domain.Product;
import com.app.store_api.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductDto getById(UUID id);

    void save (ProductDto product);

    void update (UUID id, ProductDto product);

    void deleteById(UUID id);

    List<Product> getProducts();
}
