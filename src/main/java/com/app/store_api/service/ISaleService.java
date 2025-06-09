package com.app.store_api.service;

import com.app.store_api.domain.Sale;
import com.app.store_api.dto.SaleDto;

import java.util.List;
import java.util.UUID;

public interface ISaleService {

   SaleDto getById(UUID id);

    void save (SaleDto saleDto);

    void update (UUID id, SaleDto saleDto);

    void deleteById(UUID id);

    List<Sale> getSales();
}
