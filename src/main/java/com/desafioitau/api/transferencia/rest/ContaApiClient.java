package com.desafioitau.api.transferencia.rest;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "conta-api", url = "http://localhost:9090/contas/")
public interface ContaApiClient {

    @RequestMapping(method = RequestMethod.GET, path = "{idConta}")
    ContaResponseDTO getConta(@PathVariable("idConta") String idConta);

}
