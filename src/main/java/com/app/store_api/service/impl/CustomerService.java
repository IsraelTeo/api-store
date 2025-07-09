package com.app.store_api.service.impl;

import com.app.store_api.domain.Customer;
import com.app.store_api.dto.customer.CustomerDto;
import com.app.store_api.dto.criteria.SearchCustomerCriteriaDto;
import com.app.store_api.dto.customer.CustomerResponseDto;
import com.app.store_api.exception.ApiError;
import com.app.store_api.exception.StoreException;
import com.app.store_api.persistence.repository.ICustomerRepository;
import com.app.store_api.persistence.specification.CustomerSpecification;
import com.app.store_api.service.ICustomerService;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService implements ICustomerService {

    static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    ICustomerRepository customerRepository;

    ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public CustomerResponseDto getById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            LOGGER.debug("Not exist customer with the id {}", id);
           throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }

        return conversionService.convert(customer.get(), CustomerResponseDto.class);
    }

    @Transactional
    @Override
    public CustomerResponseDto save(CustomerDto customerDto) {
        if (Objects.isNull(customerDto)) {
            LOGGER.debug("Attempted to save a null CustomerDto.");
            throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }

        LOGGER.debug("Starting conversion of CustomerDto to Customer entity.");
        Customer customer = conversionService.convert(customerDto, Customer.class);

        if (customer == null) {
            LOGGER.error("Conversion from CustomerDto to Customer entity failed.");
            throw new StoreException(ApiError.CUSTOMER_CONVERSION_FAILED);
        }

        customerRepository.save(customer);

        return conversionService.convert(customer, CustomerResponseDto.class);
    }

    @Transactional
    @Override
    public CustomerResponseDto update(UUID id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)) {
            LOGGER.debug("Customer with ID {} not found. Cannot update.", id);
            throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }

        Customer customer = conversionService.convert(customerDto, Customer.class);

        Customer customerUpdated = customerRepository.save(Objects.requireNonNull(customer));

        return conversionService.convert(customer, CustomerResponseDto.class);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        if (!customerRepository.existsById(id)) {
            LOGGER.debug("Customer with ID {} not found. Cannot delete.", id);
            throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }

        customerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponseDto> getCustomers(SearchCustomerCriteriaDto criteriaDto) {
        Pageable pageable = PageRequest.of(criteriaDto.getPageActual(), criteriaDto.getPageSize());

        List<Customer> customers = customerRepository.findAll(CustomerSpecification.withSearchCriteria(criteriaDto), pageable);

        if (customers.isEmpty()) {
            LOGGER.debug("Customers are empty list.");
            return Collections.emptyList();
        }

        return customers.stream()
                .map(customer -> conversionService.convert(customer, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }
}
