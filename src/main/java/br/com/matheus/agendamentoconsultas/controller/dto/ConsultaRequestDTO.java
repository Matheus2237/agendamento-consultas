package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidDataFutura;
import br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime;
import br.com.matheus.agendamentoconsultas.constraints.ValidDataRequisicaoConsulta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultaRequestDTO(

        @NotNull(message = "O campo 'pacienteId' é obrigatório.")
        Long pacienteId,

        @NotNull(message = "O campo 'medicoId' é obrigatório.")
        Long medicoId,

        @NotBlank(message = "O campo 'data' é obrigatório.")
        @ValidDataRequisicaoConsulta(message = "Data da consulta está no formato inválido.")
        @ValidDataFutura
        String data,

        @NotBlank(message = "O campo 'horario' é obrigatório.")
        @ValidLocalTime(message = "Horário da consulta inválido.")
        String horario
) {
}