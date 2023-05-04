package com.autorizador.api.mapper;

import com.autorizador.api.endpoint.data.CartaoRequest;
import com.autorizador.api.endpoint.data.CartaoResponse;
import com.autorizador.api.repository.entity.CartaoEntity;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    public CartaoEntity toCartaoEntity(final CartaoRequest request) {
        return CartaoEntity.builder()
                .numeroCartao(request.getNumeroCartao())
                .senha(request.getSenha())
                .build();
    }

    public CartaoResponse toCartaoResponse(final CartaoEntity entity) {
        return CartaoResponse.builder()
                .numeroCartao(entity.getNumeroCartao())
                .senha(entity.getSenha())
                .build();
    }

    public CartaoResponse toFindCartaoResponse(final CartaoEntity entity) {
        return CartaoResponse.builder()
                .numeroCartao(entity.getNumeroCartao())
                .saldo(entity.getSaldo())
                .build();
    }
}
