package com.desafioitau.api.transferencia.service;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.rest.ClienteApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteApiClient clienteApiClient;

    public ClienteResponseDTO buscaCliente(String idCliente) throws Exception {
        ClienteResponseDTO cliente = null;

        try {
            cliente = clienteApiClient.getCliente(idCliente);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar cliente");
        }

        return cliente;
    }

}
