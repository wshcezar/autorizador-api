package com.autorizador.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String messageCustom;

    public ResourceNotFoundException(String message, String messageCustom) {
        super(message);
        this.messageCustom = messageCustom;
    }
}