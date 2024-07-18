package br.com.matheus.agendamentoconsultas.exception.handler.dto;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * DTO para representar detalhes de uma consulta que não pode ser agendada.
 * </p>
 * <p>
 * Este DTO contém o motivo pelo qual a consulta não pôde ser agendada, a fim de fornecer
 * informações detalhadas sobre as exceções de consulta não agendada que podem ocorrer
 * durante operações no sistema de agendamento de consultas.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para representar detalhes de uma consulta que não pode ser agendada")
public record ConsultaNaoAgendadaDTO(

        @Schema(description = "O motivo pelo qual a consulta não pôde ser agendada",
                example = "Não há horários disponíveis para o médico nesta data e horário")
        String motivo
) {
}