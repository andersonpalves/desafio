package com.desafioitau.api.transferencia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Cliente não possui saldo disponível para efetuar transação")
public class SaldoIndisponivelException extends RuntimeException{
    private static final long serialVersionUID = 1L;
}
