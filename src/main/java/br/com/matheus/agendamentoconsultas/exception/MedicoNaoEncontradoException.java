package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicoNaoEncontradoException extends RuntimeException {

    public MedicoNaoEncontradoException() {
        super("Médico não encontrado");
    }
}