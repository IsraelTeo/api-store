package com.app.store_api.mapper;

import com.app.store_api.domain.Product;
import com.app.store_api.dto.product.ProductDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper extends Converter<ProductDto, Product> {

    @Override
    Product convert(ProductDto source);
}
