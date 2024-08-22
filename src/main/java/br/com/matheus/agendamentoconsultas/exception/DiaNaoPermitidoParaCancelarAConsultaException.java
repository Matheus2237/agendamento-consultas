package br.com.matheus.agendamentoconsultas.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * <p>
 * Exceção lançada quando o dia em que se está tentando
 * realizar o cancelamento da consulta não é permitido.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@ResponseStatus(FORBIDDEN)
public class DiaNaoPermitidoParaCancelarAConsultaException extends RuntimeException {

    /**
     * Construtor padrão que cria uma nova instância da exceção
     * com a mensagem padrão "Consulta não pode mais ser cancelada.".
     */
    public DiaNaoPermitidoParaCancelarAConsultaException() {
        super("Consulta não pode mais ser cancelada.");
    }
}