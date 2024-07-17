package br.com.matheus.agendamentoconsultas.exception.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * DTO para representar erros de validação de parâmetros da requisição.
 * </p>
 * <p>
 * Este DTO contém o parâmetro que falhou na validação e a mensagem de erro associada.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para representar erros de validação de parâmetros da requisição")
public record FailedParameterRequestValidationDTO(

        @Schema(description = "O parÂmetro que falhou na validação", example = "dia")
        String parametro,

        @Schema(description = "A mensagem de erro associada à falha", example = "Data inválida.")
        String mensagem
) {
}