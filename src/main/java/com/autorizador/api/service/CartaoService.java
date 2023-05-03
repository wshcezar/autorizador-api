package com.autorizador.api.service;

import com.autorizador.api.endpoint.data.CartaoRequest;
import com.autorizador.api.endpoint.data.CartaoResponse;
import com.autorizador.api.exception.ValidationException;
import com.autorizador.api.mapper.CartaoMapper;
import com.autorizador.api.repository.CartaoRepository;
import com.autorizador.api.repository.entity.CartaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoMapper mapper;
    private final CartaoRepository repository;

    private static final String VALIDATION_CARTAO_01 = "VALIDATION-CARTAO-01";

    public CartaoResponse create(CartaoRequest request) {
        try {
            CartaoEntity entity = repository.save(mapper.toCartaoEntity(request));
            return mapper.toCartaoResponse(entity);
        } catch (Exception ex) {
            throw new ValidationException(VALIDATION_CARTAO_01, request.getNumeroCartao());
        }
    }
}
