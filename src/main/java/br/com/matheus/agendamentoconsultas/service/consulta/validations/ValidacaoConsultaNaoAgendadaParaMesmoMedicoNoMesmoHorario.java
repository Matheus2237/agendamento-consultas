package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Implementação da interface {@link ValidacaoAgendamentoConsulta} que verifica se já existe uma consulta agendada
 * para o mesmo médico no mesmo horário.
 */
@Order(3)
@Component
public class ValidacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorario implements ValidacaoAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    @Autowired
    public ValidacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorario(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    /**
     * Valida se a consulta pode ser agendada verificando se já existe uma consulta agendada
     * para o mesmo médico no mesmo horário.
     *
     * @param consulta a consulta a ser validada
     * @throws ConsultaNaoPodeSerMarcadaException se já existir uma consulta agendada para o mesmo médico no mesmo horário
     */
    @Override
    public void validar(Consulta consulta) {
        Long medicoId = consulta.getMedico().getId();
        LocalDate data = consulta.getData();
        LocalTime horario = consulta.getHorario();
        if (consultaRepository.existsByMedicoIdAndDataAndHorario(medicoId, data, horario)) {
            throw new ConsultaNaoPodeSerMarcadaException("Já existe uma consulta agendada para o mesmo horário com o mesmo médico.");
        }
    }
}