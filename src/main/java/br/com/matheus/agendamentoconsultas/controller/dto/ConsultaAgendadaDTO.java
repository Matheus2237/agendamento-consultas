package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Consulta;

public record ConsultaAgendadaDTO(

        String pacienteNome,
        String medicoNome,
        String data,
        String horario
) {

    public ConsultaAgendadaDTO(Consulta consulta) {
        this(consulta.getPaciente().getNome(), consulta.getMedico().getNome(),
                consulta.getData().toString(), consulta.getHorario().toString());
    }
}