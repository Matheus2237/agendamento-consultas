package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.repository.HorarioAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.getDiaDaSemanaPelaData;

@Order(1)
@Component
public class ValidacaoHorarioConsultaDentroDoHorarioDeAtendimento implements ValidacaoAgendamentoConsulta {

    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    @Autowired
    public ValidacaoHorarioConsultaDentroDoHorarioDeAtendimento(HorarioAtendimentoRepository horarioAtendimentoRepository) {
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    @Override
    public void validar(Consulta consulta) {
        Long medicoId = consulta.getMedico().getId();
        DiaDaSemana diaDaSemana = getDiaDaSemanaPelaData(consulta.getData());
        HorarioAtendimento horarioAtendimento = horarioAtendimentoRepository
                .findByPrimaryKeyMedicoIdAndPrimaryKeyDiaDaSemana(medicoId, diaDaSemana)
                .orElseThrow(() -> new ConsultaNaoPodeSerMarcadaException("Médico não atende nesse dia da semana."));
        LocalTime horarioConsulta = consulta.getHorario();
        LocalTime horaInicial = horarioAtendimento.getHoraInicial();
        LocalTime horaFinal = horarioAtendimento.getHoraFinal();
        if (!isHorarioRequeridoCompativelComHorarioDeAtendimento(horarioConsulta, horaInicial, horaFinal)) {
            throw new ConsultaNaoPodeSerMarcadaException("Médico não atende no horário solicitado.");
        }
    }

    private boolean isHorarioRequeridoCompativelComHorarioDeAtendimento(LocalTime horarioConsulta,
            LocalTime horaInicial, LocalTime horaFinal) {
        return horarioConsulta.equals(horaInicial) || horarioConsulta.isAfter(horaInicial)
                && horarioConsulta.isBefore(horaFinal) || horarioConsulta.equals(horaFinal);
    }
}