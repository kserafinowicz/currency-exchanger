package com.currencyexchanger.common.errors;

import lombok.Getter;

@Getter
public class DomainError extends RuntimeException {

    private final String responseMessage;

    public DomainError(String message) {
        super(message);
        responseMessage = message;
    }
}
