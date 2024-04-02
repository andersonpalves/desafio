package com.desafioitau.api.transferencia.rest;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "bacen-api", url = "http://localhost:9090/notificacoes")
public interface BacenApiClient {

    @PostMapping()
    NotificacaoRequestDTO criaNotificacaoBacen(NotificacaoRequestDTO notificacaoRequestDTO);
}
