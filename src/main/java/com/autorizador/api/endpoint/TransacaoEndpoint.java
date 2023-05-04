package com.autorizador.api.endpoint;

import com.autorizador.api.endpoint.data.TransacaoRequest;
import com.autorizador.api.endpoint.data.TransacaoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Api(tags = "Transacoes", value = "API de Transacoes")
@RequestMapping("/transacoes")
public interface TransacaoEndpoint {

    @ApiOperation(value = "Criando uma Transacao", response = TransacaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Transacao feita com sucesso"),
            @ApiResponse(code = 400, message = "Transacao com dados invalidos"),
            @ApiResponse(code = 422, message = "Transacao com erro no processamento"),
            @ApiResponse(code = 500, message = "Transacao com erro de servidor")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    TransacaoResponse create(
            @ApiParam(value = "Payload da Transacao", required = true)
            @Valid @RequestBody final TransacaoRequest request);
}
