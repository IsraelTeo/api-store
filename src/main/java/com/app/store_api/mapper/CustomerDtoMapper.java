package com.app.store_api.mapper;

import com.app.store_api.domain.Customer;
import com.app.store_api.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper extends Converter<CustomerDto, Customer> {

    @Override
    Customer convert(CustomerDto source);
}
