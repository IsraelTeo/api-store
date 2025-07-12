package com.app.store_api.service;

import com.app.store_api.dto.sale.SaleDto;
import com.app.store_api.dto.criteria.SearchSaleCriteriaDto;

import java.util.List;
import java.util.UUID;

public interface ISaleService {

    SaleDto getById(UUID id);

    SaleDto save (SaleDto saleDto);

    SaleDto update (UUID id, SaleDto saleDto);

    SaleDto deleteById(UUID id);

    List<SaleDto> getSales(SearchSaleCriteriaDto criteriaDto);
}
