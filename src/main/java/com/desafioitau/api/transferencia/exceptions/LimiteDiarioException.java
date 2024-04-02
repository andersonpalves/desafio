package com.desafioitau.api.transferencia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Limite diário indisponível para efetuar transação")
public class LimiteDiarioException extends RuntimeException{
    private static final long serialVersionUID = 1L;
}
