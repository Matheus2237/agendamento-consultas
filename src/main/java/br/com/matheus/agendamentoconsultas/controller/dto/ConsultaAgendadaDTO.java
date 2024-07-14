package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Consulta;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de consulta agendada.
 *
 * @param pacienteNome Nome do paciente.
 * @param medicoNome   Nome do médico.
 * @param data         Data da consulta.
 * @param horario      Horário da consulta.
 */
@Schema(description = "DTO para resposta de consulta agendada")
public record ConsultaAgendadaDTO(

        @Schema(description = "Nome do paciente", example = "João da Silva")
        String pacienteNome,

        @Schema(description = "Nome do médico", example = "Dr. Carlos Souza")
        String medicoNome,

        @Schema(description = "Data da consulta", example = "2024-07-14")
        String data,

        @Schema(description = "Horário da consulta", example = "14:30")
        String horario
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de consulta.
     *
     * @param consulta A entidade de consulta.
     */
    public ConsultaAgendadaDTO(Consulta consulta) {
        this(consulta.getPaciente().getNome(), consulta.getMedico().getNome(),
                consulta.getData().toString(), consulta.getHorario().toString());
    }
}