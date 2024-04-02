package com.desafioitau.api.transferencia.controller;

import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/transferencia")
    public ResponseEntity<TransferenciaResponseDTO> efetuarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) throws Exception {

        TransferenciaResponseDTO transferencia = transferenciaService.efetuarTransferencia(transferenciaRequestDTO);
        return ResponseEntity.ok().body(transferencia);
    }
}
