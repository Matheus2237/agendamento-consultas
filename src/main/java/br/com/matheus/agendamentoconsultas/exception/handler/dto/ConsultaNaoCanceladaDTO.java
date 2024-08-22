package br.com.matheus.agendamentoconsultas.exception.handler.dto;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * DTO para representar detalhes de uma consulta que não pode ser cancelada.
 * </p>
 * <p>
 * Este DTO contém o motivo pelo qual a consulta não pôde ser cancelada, a fim de fornecer
 * informações detalhadas sobre as exceções de consulta não agendada que podem ocorrer
 * durante operações no sistema de agendamento de consultas.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para representar detalhes de uma consulta que não pode ser cancelada")
public record ConsultaNaoCanceladaDTO(

        @Schema(description = "O motivo pelo qual a consulta não pôde ser cancelada",
                example = "Consulta não pode mais ser cancelada.")
        String motivo
) {
}