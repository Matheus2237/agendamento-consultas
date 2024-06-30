package br.com.matheus.agendamentoconsultas.exception.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * DTO para representar erros de validação de requisição.
 * </p>
 * <p>
 * Este DTO contém o campo que falhou na validação e a mensagem de erro associada.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para representar erros de validação de requisição")
public record FailedRequestValidationDTO(

		@Schema(description = "O campo que falhou na validação", example = "nome")
		String campo,

		@Schema(description = "A mensagem de erro associada à falha", example = "O campo 'nome' é obrigatório")
		String mensagem
) {}