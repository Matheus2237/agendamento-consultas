package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaAgendadaDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaRequestDTO;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import br.com.matheus.agendamentoconsultas.service.consulta.validations.ValidacaoAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final List<ValidacaoAgendamentoConsulta> validacoesAgendamentoConsulta;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository,
                           List<ValidacaoAgendamentoConsulta> validacoesAgendamentoConsulta) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.validacoesAgendamentoConsulta = validacoesAgendamentoConsulta;
    }

    public ConsultaAgendadaDTO agendar(ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta = obterEntidadeConsulta(consultaRequestDTO);
        for (ValidacaoAgendamentoConsulta validacaoAgendamentoConsulta : validacoesAgendamentoConsulta) {
            validacaoAgendamentoConsulta.validar(consulta);
        }
        consultaRepository.save(consulta);
        return new ConsultaAgendadaDTO(consulta);
    }

    private Consulta obterEntidadeConsulta(ConsultaRequestDTO consultaRequestDTO) {
        final LocalDate data = LocalDate.parse(consultaRequestDTO.data());
        final LocalTime horario = LocalTime.parse(consultaRequestDTO.horario());
        final Paciente paciente = obterPacientePeloId(consultaRequestDTO.pacienteId());
        final Medico medico = consultaRequestDTO.medicoId() != 0
                ? obterMedicoPeloId(consultaRequestDTO.medicoId()) :
                obterMedicoAleatorioDisponivelParaDataEHorarioDeterminado(data, horario);
        return Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .data(data)
                .horario(horario)
                .build();
    }

    private Paciente obterPacientePeloId(Long pacienteId) {
        return pacienteRepository.findById(pacienteId).orElseThrow(PacienteNaoEncontradoException::new);
    }

    private Medico obterMedicoPeloId(Long medicoId) {
        return medicoRepository.findById(medicoId).orElseThrow(MedicoNaoEncontradoException::new);
    }

    private Medico obterMedicoAleatorioDisponivelParaDataEHorarioDeterminado(LocalDate data, LocalTime horario) {
        Optional<Medico> medicoOptional = medicoRepository.findRandomAvailableMedicoToTheSpecifiedDate(data, horario);
        return medicoOptional.orElseThrow(MedicoNaoEncontradoException::new);
    }
}