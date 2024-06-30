package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * Exceção lançada quando um médico não é encontrado no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicoNaoEncontradoException extends RuntimeException {

    /**
     * Construtor padrão que cria uma nova instância da exceção com a mensagem padrão "Médico não encontrado".
     */
    public MedicoNaoEncontradoException() {
        super("Médico não encontrado");
    }
}