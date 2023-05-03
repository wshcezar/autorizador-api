package com.autorizador.api.endpoint;

import com.autorizador.api.endpoint.controller.CartaoController;
import com.autorizador.api.endpoint.data.CartaoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class CartaoControllerTest {

    private static final String ENDPOINT = "/cartoes";

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @InjectMocks
    private CartaoController controller;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnSuccess_whenCreateCartao() throws Exception {

        CartaoRequest request = CartaoRequest.builder().numeroCartao("123456789").senha("123").build();

        mockMvc.perform(post(ENDPOINT)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("numeroCartao").isNotEmpty())
                .andExpect(jsonPath("numeroCartao").value(request.getNumeroCartao()))
                .andExpect(jsonPath("senha").isNotEmpty())
                .andExpect(jsonPath("senha").value(request.getSenha()))
                .andReturn();
    }
}