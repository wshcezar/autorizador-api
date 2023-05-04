package com.autorizador.api.endpoint.controller;

import com.autorizador.api.endpoint.CartaoEndpoint;
import com.autorizador.api.endpoint.data.CartaoRequest;
import com.autorizador.api.endpoint.data.CartaoResponse;
import com.autorizador.api.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartaoController implements CartaoEndpoint {

    private final CartaoService service;
    @Override
    public CartaoResponse create(final CartaoRequest request) {
        return service.create(request);
    }

    @Override
    public CartaoResponse findByNumeroCartao(final String numeroCartao) {
        return service.findByNumeroCartao(numeroCartao);
    }
}