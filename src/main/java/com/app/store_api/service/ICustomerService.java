package com.app.store_api.service;

import com.app.store_api.dto.customer.CustomerDto;
import com.app.store_api.dto.criteria.SearchCustomerCriteriaDto;
import com.app.store_api.dto.customer.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    CustomerResponseDto getById(UUID id);

    CustomerResponseDto save (CustomerDto customerDto);

    CustomerResponseDto update (UUID id, CustomerDto customerDto);

    void deleteById(UUID id);

    List<CustomerResponseDto> getCustomers(SearchCustomerCriteriaDto criteriaDto);
}
