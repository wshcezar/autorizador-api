package com.autorizador.api.endpoint.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoResponse {

    private String senha;
    private String numeroCartao;
}
