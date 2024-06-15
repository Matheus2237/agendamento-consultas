package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;

public record HorarioAtendimentoResponseDTO(String diaDaSemana, String horarioInicial, String horarioAtendimento) {

    public HorarioAtendimentoResponseDTO(HorarioAtendimento horarioAtendimento) {
        this(horarioAtendimento.getPrimaryKey().getDiaDaSemana().toString(),
                horarioAtendimento.getHoraInicial().toString(), horarioAtendimento.getHoraFinal().toString());
    }
}