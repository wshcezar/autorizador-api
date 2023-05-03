package com.autorizador.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private String messageCustom;

    public ValidationException(String message) { super(message); }

    public ValidationException(String message, String messageCustom) {
        super(message);
        this.messageCustom = messageCustom;
    }
}