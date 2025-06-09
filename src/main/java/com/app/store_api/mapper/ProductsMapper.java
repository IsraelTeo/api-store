package com.app.store_api.mapper;

import com.app.store_api.domain.Product;
import com.app.store_api.dto.ProductDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductsMapper extends Converter<List<Product>, List<ProductDto>> {

    @Override
    List<ProductDto> convert(List<Product> source);
}