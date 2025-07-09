package com.app.store_api.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ApiError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "There are attributes with incorrect values."),

    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The message has an incorrect format."),

    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "The customer was not found."),

    CUSTOMERS_NOT_FOUND(HttpStatus.NOT_FOUND, "No customers were found."),

    EXCEED_NUMBER_REQUEST(HttpStatus.CONFLICT, "The number of requests exceeds the limit."),

    CUSTOMER_WITH_SAME_ID(HttpStatus.CONFLICT, "The customer with the same id already exists."),

    CUSTOMER_CONVERSION_FAILED(HttpStatus.CONFLICT, "The customer conversion failed."),

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "The product was not found"),

    PRODUCT_CONVERSION_FAILED(HttpStatus.CONFLICT, "Product conversion failed"),

    SALE_NOT_FOUND(HttpStatus.NOT_FOUND, "Sale was not found"),

    ;

    HttpStatus status;

    String message;

}
