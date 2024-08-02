package br.com.matheus.agendamentoconsultas.exception.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * DTO para representar uma entidade que não foi encontrada no banco de dados.
 * </p>
 * <p>
 * Este DTO contém a mensagem informando qual entidade não foi encontrada no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para representar  uma entidade que não foi encontrada no sistema")
public record EntidadeNaoEncontradaDTO(

        @Schema(description = "A mensagem informando qual entidade não foi encontrada.",
                example = "Paciente não encontrado")
        String mensagem
) {
}
