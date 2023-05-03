package com.autorizador.api.endpoint;

import com.autorizador.api.endpoint.data.CartaoRequest;
import com.autorizador.api.endpoint.data.CartaoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Api(tags = "Cartoes", value = "API de Cartoes")
@RequestMapping("/cartoes")
public interface CartaoEndpoint {

    @ApiOperation(value = "Criando um Cartao", response = CartaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criando um cartao com sucesso"),
            @ApiResponse(code = 400, message = "Cartao com dados invalidos"),
            @ApiResponse(code = 500, message = "Cartao com erro de servidor")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<CartaoResponse> create(
            @ApiParam(value = "Payload do Cartao", required = true)
            @Valid @RequestBody final CartaoRequest request);
}
