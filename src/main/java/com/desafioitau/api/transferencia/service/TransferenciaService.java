package com.desafioitau.api.transferencia.service;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.exceptions.ContaCorrenteInativaException;
import com.desafioitau.api.transferencia.exceptions.LimiteDiarioException;
import com.desafioitau.api.transferencia.exceptions.SaldoIndisponivelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferenciaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private BacenService bacenService;

    public TransferenciaResponseDTO efetuarTransferencia(TransferenciaRequestDTO transferenciaRequestDTO) throws Exception {
        ClienteResponseDTO cliente = clienteService.buscaCliente(transferenciaRequestDTO.getIdCliente());
        ContaResponseDTO contaOrigem = contaService.buscaConta(transferenciaRequestDTO.getConta().getIdOrigem());
        if (!contaOrigem.isAtivo()) {
            throw new ContaCorrenteInativaException();
        }

        if (contaOrigem.getSaldo() < transferenciaRequestDTO.getValor()) {
            throw new SaldoIndisponivelException();
        }

        double limite = contaOrigem.getLimiteDiario();
        double valor = transferenciaRequestDTO.getValor();

        if (contaOrigem.getLimiteDiario() == 0 || limite < valor) {
            throw new LimiteDiarioException();
        }

        ContaResponseDTO contaDestino = contaService.buscaConta(transferenciaRequestDTO.getConta().getIdDestino());
        if (!contaDestino.isAtivo()) {
            throw new ContaCorrenteInativaException();
        }

        bacenService.criaNotificacaoBacen(valor, contaOrigem.getId(), contaOrigem.getId());

        TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
        transferenciaResponseDTO.setIdTransferencia(UUID.randomUUID());

        return transferenciaResponseDTO;
    }
}
