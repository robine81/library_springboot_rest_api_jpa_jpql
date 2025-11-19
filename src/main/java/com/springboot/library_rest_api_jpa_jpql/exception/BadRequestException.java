package com.springboot.library_rest_api_jpa_jpql.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
