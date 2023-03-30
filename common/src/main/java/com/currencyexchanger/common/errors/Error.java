package com.currencyexchanger.common.errors;

import lombok.Getter;

@Getter
public class Error extends RuntimeException {

    private final String responseMessage;

    public Error(String message) {
        super(message);
        responseMessage = message;
    }
}
