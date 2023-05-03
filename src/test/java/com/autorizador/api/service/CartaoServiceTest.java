package com.autorizador.api.service;

import com.autorizador.api.endpoint.data.CartaoRequest;
import com.autorizador.api.endpoint.data.CartaoResponse;
import com.autorizador.api.mapper.CartaoMapper;
import com.autorizador.api.repository.CartaoRepository;
import com.autorizador.api.repository.entity.CartaoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CartaoServiceTest {

    @Mock
    private CartaoMapper mapper;

    @Mock
    private CartaoRepository repository;

    @InjectMocks
    private CartaoService service;

    private CartaoEntity entity;
    private CartaoResponse response;
    private CartaoRequest request;

    @BeforeEach
    public void setUp() {
        entity = CartaoEntity.builder().numeroCartao("123456").senha("123").saldo(BigDecimal.valueOf(500.0)).build();
        response = CartaoResponse.builder().numeroCartao("123456").senha("123").build();
        request = CartaoRequest.builder().numeroCartao("123456").senha("123").build();
    }

    @Test
    public void deveCriarUmCartaoComSucesso() {

        try {

            when(mapper.toCartaoEntity(any())).thenReturn(entity);
            when(mapper.toCartaoResponse(any())).thenReturn(response);

            when(repository.save(any())).thenReturn(entity);

            var result = service.create(request);

            verify(mapper).toCartaoEntity(any());
            verify(mapper).toCartaoResponse(any());
            verify(repository).save(any());

            assertThat(result).isNotNull();
            assertThat(result.getNumeroCartao()).isEqualTo(response.getNumeroCartao());
            assertThat(result.getSenha()).isEqualTo(response.getSenha());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }
}
