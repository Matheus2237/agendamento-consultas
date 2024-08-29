package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidDataFutura;
import br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para requisição de agendamento de consulta.
 *
 * @param pacienteId ID do paciente.
 * @param medicoId   ID do médico.
 * @param data       Data da consulta.
 * @param horario    Horário da consulta.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para requisição de agendamento de consulta")
public record ConsultaRequestDTO(

        @Schema(description = "ID do paciente", example = "1")
        @NotNull(message = "O campo 'pacienteId' é obrigatório.")
        Long pacienteId,

        @Schema(description = "ID do médico", example = "2")
        @NotNull(message = "O campo 'medicoId' é obrigatório.")
        Long medicoId,

        @Schema(description = "Data da consulta", example = "2024-07-14")
        @NotBlank(message = "O campo 'data' é obrigatório.")
        @ValidDataFutura
        String data,

        @Schema(description = "Horário da consulta", example = "14:30")
        @NotBlank(message = "O campo 'horario' é obrigatório.")
        @ValidLocalTime(message = "Horário da consulta inválido.")
        String horario
) {
}