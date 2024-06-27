package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * Exceção lançada quando um paciente não é encontrado no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PacienteNaoEncontradoException extends RuntimeException {

    public PacienteNaoEncontradoException() {
        super("Paciente não encontrado");
    }
}