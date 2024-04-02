package com.desafioitau.api.transferencia.rest;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cliente-api", url = "http://localhost:9090/clientes/")
public interface ClienteApiClient {

    @RequestMapping(method = RequestMethod.GET, path = "{idCliente}")
    ClienteResponseDTO getCliente(@PathVariable("idCliente") String idCliente);

}
