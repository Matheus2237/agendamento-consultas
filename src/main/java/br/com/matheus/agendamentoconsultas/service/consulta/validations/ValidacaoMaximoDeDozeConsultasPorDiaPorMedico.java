package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Implementação da interface {@link ValidacaoAgendamentoConsulta} que verifica se o médico possui no máximo
 * doze consultas agendadas no mesmo dia.
 *
 * @see br.com.matheus.agendamentoconsultas.service.consulta.validations.ValidacaoAgendamentoConsulta
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Order(4)
@Component
public class ValidacaoMaximoDeDozeConsultasPorDiaPorMedico implements ValidacaoAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    @Autowired
    public ValidacaoMaximoDeDozeConsultasPorDiaPorMedico(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    /**
     * Valida se a consulta pode ser agendada verificando se o médico já possui doze consultas agendadas no mesmo dia.
     *
     * @param consulta a consulta a ser validada
     * @throws ConsultaNaoPodeSerMarcadaException se o médico já tiver doze consultas agendadas no mesmo dia
     */
    @Override
    public void validar(Consulta consulta) {
        Long medicoId = consulta.getMedico().getId();
        LocalDate data = consulta.getData();
        if (consultaRepository.existsMoreThanTwelveConsultationsForAnSpecifiedDayAndDoctor(medicoId, data)) {
            throw new ConsultaNaoPodeSerMarcadaException("Médico já possui o limite de 12 consultas agendadas nesse dia.");
        }
    }
}