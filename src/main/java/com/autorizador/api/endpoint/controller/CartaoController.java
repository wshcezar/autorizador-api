package com.autorizador.api.endpoint.controller;

import com.autorizador.api.endpoint.CartaoEndpoint;
import com.autorizador.api.endpoint.data.CartaoRequest;
import com.autorizador.api.endpoint.data.CartaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartaoController implements CartaoEndpoint {

    @Override
    public ResponseEntity<CartaoResponse> create(CartaoRequest request) {
        return ResponseEntity.ok(CartaoResponse.builder()
                .numeroCartao(request.getNumeroCartao())
                .senha(request.getSenha())
                .build());
    }
}
