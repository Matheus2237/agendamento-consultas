package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidDiaDaSemana;
import br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime;
import jakarta.validation.constraints.NotBlank;

public record HorarioAtendimentoRequestDTO(

        @ValidDiaDaSemana
        @NotBlank(message = "O campo 'diaDaSemana' é obrigatório")
        String diaDaSemana,

        @ValidLocalTime(message = "Horário inicial inválido.")
        @NotBlank(message = "O campo 'horaInicial' é obrigatório")
        String horaInicial,

        @ValidLocalTime(message = "Horário final inválido.")
        @NotBlank(message = "O campo 'horaFinal' é obrigatório")
        String horaFinal
) {}