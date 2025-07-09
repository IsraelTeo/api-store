package com.app.store_api.service;

import com.app.store_api.dto.sale.SaleDto;
import com.app.store_api.dto.criteria.SearchSaleCriteriaDto;
import com.app.store_api.dto.sale.SaleResponseDto;

import java.util.List;
import java.util.UUID;

public interface ISaleService {

    SaleResponseDto getById(UUID id);

    SaleResponseDto save (SaleDto saleDto);

    SaleResponseDto update (UUID id, SaleDto saleDto);

    SaleResponseDto deleteById(UUID id);

    List<SaleResponseDto> getSales(SearchSaleCriteriaDto criteriaDto);
}
