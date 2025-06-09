package com.app.store_api.service;

import com.app.store_api.dto.CustomerDto;
import com.app.store_api.dto.criteria.SearchCustomerCriteriaDto;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    CustomerDto getById(UUID id);

    CustomerDto save (CustomerDto customerDto);

    CustomerDto update (UUID id, CustomerDto customerDto);

    void deleteById(UUID id);

    List<CustomerDto>  getCustomers(SearchCustomerCriteriaDto criteriaDto);
}
