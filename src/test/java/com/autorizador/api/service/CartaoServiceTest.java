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
import java.util.Optional;

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

    private static final String VALIDATION_CARTAO_01 = "VALIDATION-CARTAO-01";
    private static final String VALIDATION_CARTAO_02 = "VALIDATION-CARTAO-02";

    @BeforeEach
    public void setUp() {
        entity = CartaoEntity.builder().numeroCartao("123456").senha("123").saldo(BigDecimal.valueOf(500.0)).build();
        response = CartaoResponse.builder().numeroCartao("123456").senha("123").saldo(BigDecimal.valueOf(500.0)).build();
        request = CartaoRequest.builder().numeroCartao("123456").senha("123").build();
    }

    @Test
    public void deveCriarUmCartaoComSucesso() {

        try {

            when(mapper.toCartaoEntity(any())).thenReturn(entity);
            when(mapper.toCartaoResponse(any())).thenReturn(response);

            when(repository.findById(any())).thenReturn(Optional.of(entity));
            when(repository.save(any())).thenReturn(entity);

            var result = service.create(request);

            verify(mapper).toCartaoEntity(any());
            verify(mapper).toCartaoResponse(any());
            verify(repository).findById(any());
            verify(repository).save(any());

            assertThat(result).isNotNull();
            assertThat(result.getNumeroCartao()).isEqualTo(response.getNumeroCartao());
            assertThat(result.getSenha()).isEqualTo(response.getSenha());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void deveRetornarErroQuandoCadastrarOCartao() {

        try {

            when(repository.findById(any())).thenReturn(null);
            service.create(request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findById(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(VALIDATION_CARTAO_01);
        }
    }

    @Test
    public void deveConsultarUmCartaoComSucesso() {

        try {

            when(repository.findById(any())).thenReturn(Optional.of(entity));
            when(mapper.toFindCartaoResponse(entity)).thenReturn(response);
            var result = service.findByNumeroCartao(request.getNumeroCartao());

            verify(repository).findById(any());
            verify(mapper).toFindCartaoResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getNumeroCartao()).isEqualTo(response.getNumeroCartao());
            assertThat(result.getSaldo()).isEqualTo(response.getSaldo());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void deveRetornarCartaoInexistenteQuandoConsultar() {

        try {

            when(repository.findById(any())).thenReturn(null);
            service.findByNumeroCartao(request.getNumeroCartao());

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findById(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(VALIDATION_CARTAO_02);
        }
    }
}
