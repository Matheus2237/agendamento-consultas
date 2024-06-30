package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidDiaDaSemana;
import br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para solicitação de horário de atendimento.
 *
 * @param diaDaSemana O dia da semana do horário de atendimento. Não pode ser vazio ou nulo.
 * @param horaInicial A hora inicial do horário de atendimento. Não pode ser vazio ou nulo.
 * @param horaFinal   A hora final do horário de atendimento. Não pode ser vazio ou nulo.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para solicitação de horário de atendimento")
public record HorarioAtendimentoRequestDTO(

        @ValidDiaDaSemana
        @NotBlank(message = "O campo 'diaDaSemana' é obrigatório")
        @Schema(description = "Dia da semana para atendimento", example = "SEGUNDA")
        String diaDaSemana,

        @ValidLocalTime(message = "Horário inicial inválido.")
        @NotBlank(message = "O campo 'horaInicial' é obrigatório")
        @Schema(description = "Hora inicial do atendimento", example = "08:00")
        String horaInicial,

        @ValidLocalTime(message = "Horário final inválido.")
        @NotBlank(message = "O campo 'horaFinal' é obrigatório")
        @Schema(description = "Hora final do atendimento", example = "12:00")
        String horaFinal
) {
}