package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ConsultaNaoPodeSerMarcadaException extends RuntimeException {

    public ConsultaNaoPodeSerMarcadaException(String motivo) {
        super(motivo);
    }
}