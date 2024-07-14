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

/**
 * Implementação da interface {@link ValidacaoAgendamentoConsulta} que verifica se a consulta está dentro
 * do horário de atendimento do médico.
 */
@Order(1)
@Component
public class ValidacaoHorarioConsultaDentroDoHorarioDeAtendimento implements ValidacaoAgendamentoConsulta {

    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    @Autowired
    public ValidacaoHorarioConsultaDentroDoHorarioDeAtendimento(HorarioAtendimentoRepository horarioAtendimentoRepository) {
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    /**
     * Valida se a consulta pode ser agendada verificando se está dentro do horário de atendimento do médico.
     *
     * @param consulta a consulta a ser validada
     * @throws ConsultaNaoPodeSerMarcadaException se a consulta estiver fora do horário de atendimento do médico
     */
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

    /**
     * Verifica se o horário da consulta é compatível com o horário de atendimento.
     *
     * @param horarioConsulta o horário da consulta
     * @param horaInicial a hora inicial do atendimento
     * @param horaFinal a hora final do atendimento
     * @return true se o horário da consulta for compatível com o horário de atendimento, caso contrário false
     */
    private boolean isHorarioRequeridoCompativelComHorarioDeAtendimento(LocalTime horarioConsulta,
            LocalTime horaInicial, LocalTime horaFinal) {
        return horarioConsulta.equals(horaInicial) || horarioConsulta.isAfter(horaInicial)
                && horarioConsulta.isBefore(horaFinal) || horarioConsulta.equals(horaFinal);
    }
}