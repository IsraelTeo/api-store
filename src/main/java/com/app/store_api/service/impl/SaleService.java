package com.app.store_api.service.impl;

import com.app.store_api.domain.Customer;
import com.app.store_api.domain.Product;
import com.app.store_api.domain.Sale;
import com.app.store_api.dto.sale.SaleDto;
import com.app.store_api.dto.criteria.SearchSaleCriteriaDto;
import com.app.store_api.dto.sale.SaleResponseDto;
import com.app.store_api.exception.ApiError;
import com.app.store_api.exception.StoreException;
import com.app.store_api.persistence.repository.ISaleRepository;
import com.app.store_api.persistence.specification.SaleSpecification;
import com.app.store_api.service.ISaleService;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SaleService implements ISaleService {

    static Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    ISaleRepository saleRepository;

    ConversionService conversionService;

    ProductService productService;

    CustomerService customerService;

    @Transactional(readOnly = true)
    @Override
    public SaleResponseDto getById(UUID id) {
        Optional<Sale> sale = saleRepository.findById(id);

        if (sale.isEmpty()) {
            LOGGER.debug("Sale with ID {} not found.", id);
            throw new StoreException(ApiError.SALE_NOT_FOUND);
        }

        return conversionService.convert(sale.get(), SaleResponseDto.class);
    }

    @Transactional
    @Override
    public SaleResponseDto save(SaleDto saleDto) {
        if (saleDto == null) {
            LOGGER.debug("Attempted to save a null SaleDto.");
            throw new StoreException(ApiError.SALE_NOT_FOUND);
        }

        Customer customerFound = conversionService.convert(customerService.getById(saleDto.customerId()), Customer.class);

        List<Product> products = productService.findProductsById(saleDto.productsId());

        BigDecimal totalAmount = productService.calculateTotalPrice(products);

        Sale sale = Sale.builder()
                .customer(customerFound)
                .products(products)
                .totalAmount(totalAmount)
                .creationDate(LocalDate.now())
                .build();

        saleRepository.save(sale);

        return conversionService.convert(sale, SaleResponseDto.class);
    }

    @Transactional
    @Override
    public SaleResponseDto update(UUID id, SaleDto saleDto) {
        Sale saleFound = saleRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.debug("Sale with ID {} not found..", id);
                    return new StoreException(ApiError.SALE_NOT_FOUND);
                });

        Customer customerFound = conversionService.convert(customerService.getById(saleDto.customerId()), Customer.class);

        List<Product> products = productService.findProductsById(saleDto.productsId());

        BigDecimal totalAmount = productService.calculateTotalPrice(products);

        saleFound.setCustomer(customerFound);
        saleFound.setProducts(products);
        saleFound.setTotalAmount(totalAmount);

        Sale updated = saleRepository.save(saleFound);

        return conversionService.convert(updated, SaleResponseDto.class);
    }

    @Transactional
    @Override
    public SaleResponseDto deleteById(UUID id) {
        Optional<Sale> sale = saleRepository.findById(id);

        if (sale.isEmpty()) {
            LOGGER.debug("Sale with ID {} not found. Cannot delete.", id);
            throw new StoreException(ApiError.SALE_NOT_FOUND);
        }

        saleRepository.deleteById(id);
        return conversionService.convert(sale.get(), SaleResponseDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SaleResponseDto> getSales(SearchSaleCriteriaDto criteriaDto) {
        Pageable pageable = PageRequest.of(criteriaDto.getPageActual(), criteriaDto.getPageSize());

        List<Sale> sales = saleRepository.findAll(SaleSpecification.withSearchCriteria(criteriaDto), pageable);

        if (sales.isEmpty()) {
            LOGGER.debug("Sales are empty list.");
            return Collections.emptyList();
        }

        return sales.stream()
                .map(sale -> conversionService.convert(sale, SaleResponseDto.class))
                .collect(Collectors.toList());
    }
}
