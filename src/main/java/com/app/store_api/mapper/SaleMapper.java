package com.app.store_api.mapper;

import com.app.store_api.domain.Sale;
import com.app.store_api.dto.sale.SaleResponseDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface SaleMapper extends Converter<Sale, SaleResponseDto> {

    @Override
    SaleResponseDto convert(Sale source);
}
