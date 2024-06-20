package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;

public record HorarioAtendimentoResponseDTO(String diaDaSemana, String horaInicial, String horaFinal) {

    public HorarioAtendimentoResponseDTO(HorarioAtendimento horarioAtendimento) {
        this(horarioAtendimento.getPrimaryKey().getDiaDaSemana().toString(),
                horarioAtendimento.getHoraInicial().toString(), horarioAtendimento.getHoraFinal().toString());
    }
}