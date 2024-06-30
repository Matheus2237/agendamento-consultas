package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de horário de atendimento.
 *
 * @param diaDaSemana O dia da semana do horário de atendimento.
 * @param horaInicial A hora inicial do horário de atendimento.
 * @param horaFinal   A hora final do horário de atendimento.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para resposta de horário de atendimento")
public record HorarioAtendimentoResponseDTO(

        @Schema(description = "Dia da semana para atendimento", example = "SEGUNDA")
        String diaDaSemana,

        @Schema(description = "Hora inicial do atendimento", example = "08:00")
        String horaInicial,

        @Schema(description = "Hora final do atendimento", example = "12:00")
        String horaFinal
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de horário de atendimento.
     *
     * @param horarioAtendimento A entidade de horário de atendimento.
     */
    public HorarioAtendimentoResponseDTO(HorarioAtendimento horarioAtendimento) {
        this(horarioAtendimento.getPrimaryKey().getDiaDaSemana().toString(),
                horarioAtendimento.getHoraInicial().toString(), horarioAtendimento.getHoraFinal().toString());
    }
}