package com.app.store_api.mapper;

import com.app.store_api.domain.Customer;
import com.app.store_api.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomersMapper extends Converter<List<Customer>, List<CustomerDto>> {

    @Override
    List<CustomerDto> convert(List<Customer> source);
}
