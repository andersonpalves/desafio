package com.desafioitau.api.transferencia.service;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.rest.ClienteApiClient;
import com.desafioitau.api.transferencia.rest.ContaApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    private ContaApiClient contaApiClient;

    public ContaResponseDTO buscaConta(String idConta) throws Exception {
        ContaResponseDTO conta = null;

        try {
            conta = contaApiClient.getConta(idConta);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar conta");
        }

        return conta;
    }
}
