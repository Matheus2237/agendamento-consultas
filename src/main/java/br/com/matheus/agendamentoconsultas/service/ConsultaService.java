package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaAgendadaDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaRequestDTO;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoEncontradaException;
import br.com.matheus.agendamentoconsultas.exception.DiaNaoPermitidoParaCancelarAConsultaException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Serviço para operações relacionadas a consultas médicas.
 * </p>
 * <p>
 * Este serviço gerencia operações de agendamento, cancelamento e geração de relatórios
 * de consultas médicas.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final List<ValidacaoAgendamentoConsulta> validacoesAgendamentoConsulta;
    private final Clock clock;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository,
                           List<ValidacaoAgendamentoConsulta> validacoesAgendamentoConsulta, Clock clock) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.validacoesAgendamentoConsulta = validacoesAgendamentoConsulta;
        this.clock = clock;
    }

    /**
     * Agenda uma nova consulta com base nos dados fornecidos.
     *
     * @param consultaRequestDTO Os dados da consulta a ser agendada
     * @return A consulta agendada
     */
    public Consulta agendar(ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta = obterEntidadeConsulta(consultaRequestDTO);
        for (ValidacaoAgendamentoConsulta validacaoAgendamentoConsulta : validacoesAgendamentoConsulta) {
            validacaoAgendamentoConsulta.validar(consulta);
        }
        return consultaRepository.save(consulta);
    }

    /**
     * Obtém a entidade de consulta com base nos dados fornecidos.
     *
     * @param consultaRequestDTO Os dados da consulta
     * @return A entidade de consulta
     */
    private Consulta obterEntidadeConsulta(ConsultaRequestDTO consultaRequestDTO) {
        final LocalDate data = LocalDate.parse(consultaRequestDTO.data());
        final LocalTime horario = LocalTime.parse(consultaRequestDTO.horario());
        final Paciente paciente = obterPacientePeloId(consultaRequestDTO.pacienteId());
        final Medico medico = consultaRequestDTO.medicoId() != 0
                ? obterMedicoPeloId(consultaRequestDTO.medicoId())
                : obterMedicoAleatorioDisponivelParaDataEHorarioDeterminado(data, horario);
        return new Consulta(medico, paciente, data, horario);
    }

    /**
     * Obtém o paciente com base no ID fornecido.
     *
     * @param pacienteId O ID do paciente
     * @return O paciente encontrado
     * @throws PacienteNaoEncontradoException Se o paciente com o ID especificado não for encontrado
     */
    private Paciente obterPacientePeloId(Long pacienteId) {
        return pacienteRepository.findById(pacienteId).orElseThrow(PacienteNaoEncontradoException::new);
    }

    /**
     * Obtém o médico com base no ID fornecido.
     *
     * @param medicoId O ID do médico
     * @return O médico encontrado
     * @throws MedicoNaoEncontradoException Se o médico com o ID especificado não for encontrado
     */
    private Medico obterMedicoPeloId(Long medicoId) {
        return medicoRepository.findById(medicoId).orElseThrow(MedicoNaoEncontradoException::new);
    }

    /**
     * <p>
     * Obtém um médico aleatório disponível para a data e horário solicitados.
     * </p>
     * <p>
     * Traz apenas um resultado dentro das regras de agendamento. Considera apenas os médicos que
     * não estão com doze ou mais consultas agendadas para aquele dia e que também está com
     * disponibilidade para atender no horário solicitado.
     * </p>
     *
     * @param data    A data da consulta
     * @param horario O horário da consulta
     * @return Um médico disponível
     * @throws MedicoNaoEncontradoException Se nenhum médico disponível for encontrado para a data e horário especificados
     */
    private Medico obterMedicoAleatorioDisponivelParaDataEHorarioDeterminado(LocalDate data, LocalTime horario) {
        Optional<Medico> medicoOptional = medicoRepository.findRandomAvailableMedicoToTheSpecifiedDate(data, horario);
        return medicoOptional.orElseThrow(MedicoNaoEncontradoException::new);
    }

    /**
     * Cancela uma consulta com base no ID fornecido e na data da tentativa.
     *
     * @param id O ID da consulta a ser cancelada.
     * @throws ConsultaNaoEncontradaException                se o paciente com o ID especificado não for encontrado.
     * @throws DiaNaoPermitidoParaCancelarAConsultaException se a tentativa de cancelamento é na data da consulta ou posterior.
     */
    public void cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(ConsultaNaoEncontradaException::new);
        if (!podeSerCanceladaNaDataDeHoje(consulta)) {
            throw new DiaNaoPermitidoParaCancelarAConsultaException();
        }
        consultaRepository.deleteById(consulta.getId());
    }

    /**
     * Verifica se a consulta pode ser cancelada na data de hoje.
     *
     * @param consulta A consulta que será verificada a data agendada
     * @return {@code true} se a consulta pode ser cancelada na data de hoja e {@code false} caso contrário.
     */
    private boolean podeSerCanceladaNaDataDeHoje(Consulta consulta) {
        LocalDate hoje = LocalDate.now(clock);
        LocalDate dataDaConsulta = consulta.getData();
        return hoje.isBefore(dataDaConsulta);
    }

    /**
     * Busca uma página de consultas agendadas para uma data específica.
     *
     * @param data     a data das consultas a serem buscadas no formato YYYY-MM-DD.
     * @param pageable o objeto de paginação que especifica a página, o tamanho da página e a ordenação.
     * @return uma página de {@link ConsultaAgendadaDTO} que representa as consultas agendadas na data especificada.
     */
    public Page<ConsultaAgendadaDTO> visualizarConsultasDoDia(String data, Pageable pageable) {
        Page<Consulta> consultas = consultaRepository.findByData(LocalDate.parse(data), pageable);
        return consultas.map(ConsultaAgendadaDTO::new);
    }

    /**
     * Busca uma página de consultas agendadas em um mês específico.
     *
     * @param mes      a mês das consultas a serem buscadas.
     * @param ano      o ano das consultas a serem buscadas.
     * @param pageable o objeto de paginação que especifica a página, o tamanho da página e a ordenação.
     * @return uma página de {@link ConsultaAgendadaDTO} que representa as consultas agendadas para o mês especificado.
     */
    public Page<ConsultaAgendadaDTO> visualizarConsultasDoMes(int mes, int ano, Pageable pageable) {
        Page<Consulta> consultas = consultaRepository.findAllByMonthAndYear(mes, ano, pageable);
        return consultas.map(ConsultaAgendadaDTO::new);
    }
}