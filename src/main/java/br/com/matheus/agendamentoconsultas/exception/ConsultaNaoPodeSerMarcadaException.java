package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * <p>
 * Exceção lançada quando um médico não é encontrado no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@ResponseStatus(UNPROCESSABLE_ENTITY)
public class ConsultaNaoPodeSerMarcadaException extends RuntimeException {

    /**
     * Construtor padrão que cria uma nova instância da exceção com a mensagem padrão "Médico não encontrado".
     */
    public ConsultaNaoPodeSerMarcadaException(String motivo) {
        super(motivo);
    }
}