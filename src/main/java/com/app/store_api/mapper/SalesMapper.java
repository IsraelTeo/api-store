package com.app.store_api.mapper;

import com.app.store_api.domain.Sale;
import com.app.store_api.dto.SaleDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesMapper extends Converter<List<Sale>, List<SaleDto>>{

    @Override
    List<SaleDto> convert(List<Sale> source);
}
