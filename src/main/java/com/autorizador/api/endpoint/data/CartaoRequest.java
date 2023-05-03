package com.autorizador.api.endpoint.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequest {

    @NotEmpty(message = "{field.notempty}")
    private String numeroCartao;

    @NotEmpty(message = "{field.notempty}")
    private String senha;
}
