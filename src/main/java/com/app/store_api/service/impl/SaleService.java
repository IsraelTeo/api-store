package com.app.store_api.service.impl;

import com.app.store_api.domain.Sale;
import com.app.store_api.dto.SaleDto;
import com.app.store_api.persistence.repository.ISaleRepository;
import com.app.store_api.service.ISaleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SaleService implements ISaleService {

    static Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    ISaleRepository saleRepository;

    ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public SaleDto getById(UUID id) {
        return null;
    }

    @Transactional
    @Override
    public void save(SaleDto saleDto) {

    }

    @Transactional
    @Override
    public void update(UUID id, SaleDto saleDto) {

    }

    @Transactional
    @Override
    public void deleteById(UUID id) {

    }

    @Transactional(readOnly = true)
    @Override
    public List<Sale> getSales() {
        return List.of();
    }
}
