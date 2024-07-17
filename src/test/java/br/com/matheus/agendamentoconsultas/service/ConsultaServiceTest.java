package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaAgendadaDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaRequestDTO;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoEncontradaException;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import br.com.matheus.agendamentoconsultas.service.consulta.validations.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepositoryMock;

    @Mock
    private PacienteRepository pacienteRepositoryMock;

    @Mock
    private MedicoRepository medicoRepositoryMock;

    @Mock
    private Paciente pacienteMock;

    @Mock
    private Medico medicoMock;

    @Mock
    private ValidacaoHorarioConsultaDentroDoHorarioDeAtendimento validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock;

    @Mock
    private ValidacaoUmaConsultaPorDiaPorPaciente validacaoUmaConsultaPorDiaPorPacienteMock;

    @Mock
    private ValidacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorario validacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorarioMock;

    @Mock
    private ValidacaoMaximoDeDozeConsultasPorDiaPorMedico validacaoMaximoDeDozeConsultasPorDiaPorMedicoMock;

    private List<ValidacaoAgendamentoConsulta> validacoesMock;

    @Mock
    private Pageable pageableMock;

    private final Long consultaId = 1L;
    private final Long pacienteId = 1L;
    private final Long medicoId = 1L;
    private final String pacienteNome = "Matheus";
    private final String medicoNome = "Pedro";
    private final String data = LocalDate.now().plusDays(1).toString();
    private final String horario = "14:00";

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = openMocks(this);
        validacoesMock = Arrays.asList(
                validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock,
                validacaoUmaConsultaPorDiaPorPacienteMock,
                validacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorarioMock,
                validacaoMaximoDeDozeConsultasPorDiaPorMedicoMock
        );
        consultaService = new ConsultaService(
                consultaRepositoryMock,
                pacienteRepositoryMock,
                medicoRepositoryMock,
                validacoesMock
        );
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        mocks.close();
    }

    @Test
    void deveAgendarUmaConsultaComDadosValidos() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        when(consultaRepositoryMock.save(any(Consulta.class))).thenAnswer(i -> i.getArgument(0));
        when(pacienteMock.getNome()).thenReturn(pacienteNome);
        when(medicoMock.getNome()).thenReturn(medicoNome);
        doNothing().when(validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock).validar(any(Consulta.class));
        doNothing().when(validacaoUmaConsultaPorDiaPorPacienteMock).validar(any(Consulta.class));
        doNothing().when(validacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorarioMock).validar(any(Consulta.class));
        doNothing().when(validacaoMaximoDeDozeConsultasPorDiaPorMedicoMock).validar(any(Consulta.class));
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        Consulta consultaAgendada = consultaService.agendar(consultaRequestDTO);
        assertAll("Dados da consulta agendada devem ser consistente com o que foi solicitado.",
                () -> assertEquals(pacienteNome, consultaAgendada.getPaciente().getNome(), "Nome do paciente deve ser consistente com o id."),
                () -> assertEquals(medicoNome, consultaAgendada.getMedico().getNome(), "Nome do medico deve ser consistente com o id."),
                () -> assertEquals(data, consultaAgendada.getData().toString(), "Data da consulta deve ser a mesmo."),
                () -> assertEquals(horario, consultaAgendada.getHorario().toString(), "Horario da consulta deve ser a mesmo.")
        );
    }

    @Test
    void deveAgendarUmaConsultaComDadosValidosEMedicoDefinidoPeloSistema() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findRandomAvailableMedicoToTheSpecifiedDate(LocalDate.parse(data), LocalTime.parse(horario))).thenReturn(Optional.of(medicoMock));
        when(consultaRepositoryMock.save(any(Consulta.class))).thenAnswer(i -> i.getArgument(0));
        when(pacienteMock.getNome()).thenReturn(pacienteNome);
        when(medicoMock.getNome()).thenReturn(medicoNome);
        doNothing().when(validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock).validar(any(Consulta.class));
        doNothing().when(validacaoUmaConsultaPorDiaPorPacienteMock).validar(any(Consulta.class));
        doNothing().when(validacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorarioMock).validar(any(Consulta.class));
        doNothing().when(validacaoMaximoDeDozeConsultasPorDiaPorMedicoMock).validar(any(Consulta.class));
        ConsultaRequestDTO consultaRequestDTO = new ConsultaRequestDTO(pacienteId, 0L, data, horario);
        Consulta consultaAgendada = consultaService.agendar(consultaRequestDTO);
        assertAll("Dados da consulta agendada devem ser consistente com o que foi solicitado.",
                () -> assertEquals(pacienteNome, consultaAgendada.getPaciente().getNome(), "Nome do paciente deve ser consistente com o id."),
                () -> assertEquals(medicoNome, consultaAgendada.getMedico().getNome(), "Nome do medico deve ser consistente com o id."),
                () -> assertEquals(data, consultaAgendada.getData().toString(), "Data da consulta deve ser a mesmo."),
                () -> assertEquals(horario, consultaAgendada.getHorario().toString(), "Horario da consulta deve ser a mesmo.")
        );
    }

    @Test
    void deveLancarExcecaoAoTentarAgendarUmaConsultaQuandoOPacienteNaoEstiverCadastradosNoSistema() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenThrow(PacienteNaoEncontradoException.class);
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        assertThrows(PacienteNaoEncontradoException.class, () -> consultaService.agendar(consultaRequestDTO),
                "Deve lançar uma PacienteNaoEncontradoException.");
    }

    @Test
    void deveLancarExcecaoAoTentarAgendarUmaConsultaQuandoOMedicoNaoEstiverCadastradosNoSistema() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenThrow(MedicoNaoEncontradoException.class);
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        assertThrows(MedicoNaoEncontradoException.class, () -> consultaService.agendar(consultaRequestDTO),
                "Deve lançar uma MedicoNaoEncontradoException.");
    }

        @Test
    void deveLancarExcecaoQuandoOHorarioDaConsultaEstiverForaDoHorarioDeAtendimentoDoMedico() {
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        doThrow(ConsultaNaoPodeSerMarcadaException.class).when(validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock).validar(any(Consulta.class));
        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO),
                "Deve lançar uma ConsultaNaoPodeSerMarcadaException.");
    }


    @Test
    void deveLancarExcecaoQuandoOPacienteJaTemUmaConsultaAgendadaNoMesmoDia() {
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        doNothing().when(validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock).validar(any(Consulta.class));
        doThrow(ConsultaNaoPodeSerMarcadaException.class).when(validacaoUmaConsultaPorDiaPorPacienteMock).validar(any(Consulta.class));
        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO),
                "Deve lançar uma ConsultaNaoPodeSerMarcadaException.");
    }

    @Test
    void deveLancarExcecaoQuandoOMedicoJaTemUmaConsultaAgendadaNoMesmoHorario() {
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        doNothing().when(validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock).validar(any(Consulta.class));
        doNothing().when(validacaoUmaConsultaPorDiaPorPacienteMock).validar(any(Consulta.class));
        doThrow(ConsultaNaoPodeSerMarcadaException.class).when(validacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorarioMock).validar(any(Consulta.class));
        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO),
                "Deve lançar uma ConsultaNaoPodeSerMarcadaException.");
    }

    @Test
    void deveLancarUmaExcecaoQuandoOMedicoJaTemDozeOuMaisConsultasAgendadasNoMesmoDia() {
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        doNothing().when(validacaoHorarioConsultaDentroDoHorarioDeAtendimentoMock).validar(any(Consulta.class));
        doNothing().when(validacaoUmaConsultaPorDiaPorPacienteMock).validar(any(Consulta.class));
        doNothing().when(validacaoConsultaNaoAgendadaParaMesmoMedicoNoMesmoHorarioMock).validar(any(Consulta.class));
        doThrow(ConsultaNaoPodeSerMarcadaException.class).when(validacaoMaximoDeDozeConsultasPorDiaPorMedicoMock).validar(any(Consulta.class));
        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO),
                "Deve lançar uma ConsultaNaoPodeSerMarcadaException.");
    }

    @Test
    void deveCancelarUmaConsultaPeloSeuId() {
        Consulta consulta = getConsulta();
        when(consultaRepositoryMock.findById(consultaId)).thenReturn(Optional.of(consulta));
        consultaService.cancelar(consultaId);
        verify(consultaRepositoryMock).deleteById(consultaId);
    }

    @Test
    void deveRetornarUmaConsultaNaoEncontradoExceptionAoTentarCancelarUmaConsultaNaoExistenteNoBancoDeDados() {
        when(consultaRepositoryMock.findById(consultaId)).thenReturn(Optional.empty());
        assertThrows(ConsultaNaoEncontradaException.class, () -> consultaService.cancelar(consultaId));
    }

    @Test
    void deveRetornarUmaPaginaDeConsultasAgendadasParaUmDiaEspecifico() {
        List<Consulta> consultas = Collections.singletonList(getConsulta());
        Page<Consulta> consultasPage = new PageImpl<>(consultas, pageableMock, consultas.size());
        when(consultaRepositoryMock.findByData(LocalDate.parse(data), pageableMock)).thenReturn(consultasPage);
        Page<ConsultaAgendadaDTO> consultasAgendadasPage = consultaService.visualizarConsultasDoDia(data, pageableMock);
        assertAll("Retorno deve ser uma paginação válida.",
                () -> assertNotNull(consultasAgendadasPage, "Paginação não é nula."),
                () -> assertNotNull(consultasAgendadasPage.getContent().getFirst(), "O conteúdo da paginação não é nulo"),
                () -> assertEquals(1, consultasAgendadasPage.getTotalElements(), "O número total de elementos deve ser um."),
                () -> assertInstanceOf(ConsultaAgendadaDTO.class, consultasAgendadasPage.getContent().getFirst(), "O elemento da paginação é uma instância de um ResponseTodosPacientes DTO.")
        );
    }

    private Consulta getConsulta() {
        return Consulta.builder()
                .id(consultaId)
                .medico(medicoMock)
                .paciente(pacienteMock)
                .data(LocalDate.parse(data))
                .horario(LocalTime.parse(horario))
                .build();
    }

    private ConsultaRequestDTO getConsultaRequestDTO() {
        return new ConsultaRequestDTO(pacienteId, medicoId, data, horario);
    }
}