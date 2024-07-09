package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Order(2)
@Component
public class ValidacaoUmaConsultaPorDiaPorPaciente implements ValidacaoAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    @Autowired
    public ValidacaoUmaConsultaPorDiaPorPaciente(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(Consulta consulta) {
        Long pacienteId = consulta.getPaciente().getId();
        LocalDate data = consulta.getData();
        if (consultaRepository.existsByPacienteIdAndData(pacienteId, data)) {
            throw new ConsultaNaoPodeSerMarcadaException("Paciente já possui consulta agendada nesse dia.");
        }
    }
}