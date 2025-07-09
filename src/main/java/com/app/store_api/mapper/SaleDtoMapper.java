package com.app.store_api.mapper;

import com.app.store_api.domain.Sale;
import com.app.store_api.dto.sale.SaleDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface SaleDtoMapper extends Converter<SaleDto, Sale> {

    @Override
    Sale convert(SaleDto source);
}
