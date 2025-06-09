package com.app.store_api.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreException extends RuntimeException {

    HttpStatus status;

    private String description;

    List<String> reasons;

    public StoreException(ApiError error) {
        this.status = error.getStatus();
        this.description = error.getMessage();
    }
}
