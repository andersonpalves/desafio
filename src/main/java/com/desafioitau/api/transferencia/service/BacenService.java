package com.desafioitau.api.transferencia.service;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.rest.BacenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BacenService {

    @Autowired
    private BacenApiClient bacenApiClient;

    public void criaNotificacaoBacen(double valor, String idOrigem, String idDestino) throws Exception {
        NotificacaoRequestDTO.Conta conta = new NotificacaoRequestDTO.Conta();
        conta.setIdOrigem(idOrigem);
        conta.setIdDestino(idDestino);

        NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();
        notificacaoRequestDTO.setValor(valor);
        notificacaoRequestDTO.setConta(conta);

        try {
            bacenApiClient.criaNotificacaoBacen(notificacaoRequestDTO);
        } catch (Exception e) {
            //Enviar para DLQ caso erro retorno for 429
            throw new Exception("Erro ao gerar notificação ao Bacen");
        }
    }

}
