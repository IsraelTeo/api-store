package com.app.store_api.controller;

import com.app.store_api.controller.resource.ReservationResource;
import com.app.store_api.dto.CustomerDto;
import com.app.store_api.dto.criteria.SearchCustomerCriteriaDto;
import com.app.store_api.exception.ApiError;
import com.app.store_api.exception.StoreException;
import com.app.store_api.service.impl.CustomerService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/customers")
public class CustomerController implements ReservationResource {

    static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(SearchCustomerCriteriaDto criteriaDto) {
        LOGGER.info("Obtain all the customers");
        List<CustomerDto> response = customerService.getCustomers(criteriaDto);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@Min(1) @PathVariable("id") UUID id) {
        LOGGER.info("Obtain information from a customer with {}", id);
        CustomerDto response = customerService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RateLimiter(name = "post-customer", fallbackMethod = "fallBackPost")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
        LOGGER.info("Saving new customer");
        CustomerDto response = customerService.save(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Min(1) @PathVariable("id") UUID id, @RequestBody @Valid CustomerDto customerDto) {
        LOGGER.info("Updating a customer with {}", id);
        CustomerDto response = customerService.update(id, customerDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@Min(1) @PathVariable UUID id) {
        LOGGER.info("Deleting a customer with {}", id);
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<CustomerDto> fallBackPost() {
        LOGGER.debug("calling to fallbackPost");
        throw new StoreException(ApiError.EXCEED_NUMBER_REQUEST);
    }
}
