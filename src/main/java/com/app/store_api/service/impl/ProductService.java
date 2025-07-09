package com.app.store_api.service.impl;

import com.app.store_api.domain.Product;
import com.app.store_api.dto.product.ProductDto;
import com.app.store_api.dto.criteria.SearchProductCriteriaDto;
import com.app.store_api.dto.product.ProductResponseDto;
import com.app.store_api.exception.ApiError;
import com.app.store_api.exception.StoreException;
import com.app.store_api.persistence.repository.IProductRepository;
import com.app.store_api.persistence.specification.ProductSpecification;
import com.app.store_api.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {

    static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    IProductRepository productRepository;

    ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDto getById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            LOGGER.debug("Product with ID {} not found..", id);
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        return conversionService.convert(product.get(), ProductResponseDto.class);
    }

    @Transactional
    @Override
    public ProductResponseDto save(ProductDto productDto) {
        if (Objects.isNull(productDto)) {
            LOGGER.debug("Attempted to save a null ProductDto.");
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        LOGGER.debug("Starting conversion of ProductDto to Product entity.");
        Product product = conversionService.convert(productDto, Product.class);

        if (product == null) {
            LOGGER.error("Conversion from ProductDto to Product entity failed.");
            throw new StoreException(ApiError.PRODUCT_CONVERSION_FAILED);
        }

        productRepository.save(product);

        return conversionService.convert(product, ProductResponseDto.class);
    }

    @Transactional
    @Override
    public ProductResponseDto update(UUID id, ProductDto productDto) {
        if (!productRepository.existsById(id)) {
            LOGGER.debug("Product with ID {} not found. Cannot update.", id);
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        Product product = conversionService.convert(productDto, Product.class);

        Product updatedProduct = productRepository.save(Objects.requireNonNull(product));

        return conversionService.convert(updatedProduct, ProductResponseDto.class);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        if (!productRepository.existsById(id)) {
            LOGGER.debug("Product with ID {} not found. Cannot delete.", id);
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponseDto> getProducts(SearchProductCriteriaDto criteriaDto) {
        Pageable pageable = PageRequest.of(criteriaDto.getPageActual(), criteriaDto.getPageSize());

        List<Product> products = productRepository.findAll(ProductSpecification.withSearchCriteria(criteriaDto), pageable);

        if (products.isEmpty()) {
            LOGGER.debug("Products are empty list.");
            return Collections.emptyList();
        }

        return products.stream()
                .map(product -> conversionService.convert(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<Product> findProductsById(List<UUID> productsId){
        LOGGER.debug("Entering findProductsById with {} product IDs", productsId.size());

        return productsId.stream()
                .map(id -> productRepository.findById(id).orElseThrow(() -> {
                    LOGGER.debug("Product with ID {} not found.", id);
                    return new StoreException(ApiError.PRODUCT_NOT_FOUND);
                }))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTotalPrice(List<Product> products){
        LOGGER.debug("Entering calculateTotalAmount with {} products", products.size());

        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
