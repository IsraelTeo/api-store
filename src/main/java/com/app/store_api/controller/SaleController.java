package com.app.store_api.controller;

import com.app.store_api.dto.product.ProductDto;
import com.app.store_api.dto.sale.SaleDto;
import com.app.store_api.dto.criteria.SearchSaleCriteriaDto;
import com.app.store_api.exception.ApiError;
import com.app.store_api.exception.StoreException;
import com.app.store_api.service.impl.SaleService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
@RequestMapping("/sales")
public class SaleController {

    static Logger LOGGER = LoggerFactory.getLogger(SaleController.class);

    SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDto>> getSales(SearchSaleCriteriaDto criteriaDto) {
        LOGGER.info("Fetching all sales");
        List<SaleDto> response = saleService.getSales(criteriaDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSaleById(@Min(1) @PathVariable("id") UUID id) {
        LOGGER.info("Fetching sale with ID {}", id);
        SaleDto response = saleService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RateLimiter(name = "post-sale", fallbackMethod = "fallBackPost")
    public ResponseEntity<SaleDto> saveSale(@RequestBody @Valid SaleDto saleDto) {
        LOGGER.info("Saving new sale");
        SaleDto response = saleService.save(saleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> saleProduct(@Min(1) @PathVariable("id") UUID id, @RequestBody @Valid SaleDto saleDto) {
        LOGGER.info("Updating sale with ID {}", id);
        SaleDto response = saleService.update(id, saleDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@Min(1) @PathVariable UUID id) {
        LOGGER.info("Deleting sale with ID {}", id);
        saleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ProductDto> fallBackPost() {
        LOGGER.debug("Fallback for saving sale triggered");
        throw new StoreException(ApiError.EXCEED_NUMBER_REQUEST);
    }
}
