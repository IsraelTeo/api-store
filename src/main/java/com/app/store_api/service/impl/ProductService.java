package com.app.store_api.service.impl;

import com.app.store_api.domain.Product;
import com.app.store_api.dto.ProductDto;
import com.app.store_api.persistence.repository.IProductRepository;
import com.app.store_api.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {

    static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    IProductRepository productRepository;

    ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public ProductDto getById(UUID id) {
        return null;
    }

    @Transactional
    @Override
    public void save(ProductDto product) {

    }

    @Transactional
    @Override
    public void update(UUID id, ProductDto product) {

    }

    @Transactional
    @Override
    public void deleteById(UUID id) {

    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProducts() {
        return List.of();
    }
}
