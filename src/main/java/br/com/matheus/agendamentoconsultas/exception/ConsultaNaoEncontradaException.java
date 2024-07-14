package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * <p>
 * Exceção lançada quando uma consulta não é encontrada no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@ResponseStatus(NOT_FOUND)
public class ConsultaNaoEncontradaException extends RuntimeException {

    /**
     * Construtor padrão que cria uma nova instância da exceção com a mensagem padrão "Consulta não encontrada".
     */
    public ConsultaNaoEncontradaException() {
        super("Consulta não encontrada");
    }
}