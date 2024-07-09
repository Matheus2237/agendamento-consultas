package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaAgendadaDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaRequestDTO;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
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
        when(pacienteMock.getNome()).thenReturn(pacienteNome);
        when(medicoMock.getNome()).thenReturn(medicoNome);

        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        ConsultaAgendadaDTO consultaAgendada = consultaService.agendar(consultaRequestDTO);


        assertAll("Dados da consulta agendada devem ser consistente com o que foi solicitado.",
                () -> assertEquals(pacienteNome, consultaAgendada.pacienteNome(), "Nome do paciente deve ser consistente com o id."),
                () -> assertEquals(medicoNome, consultaAgendada.medicoNome(), "Nome do medico deve ser consistente com o id."),
                () -> assertEquals(data, consultaAgendada.data(), "Data da consulta deve ser a mesmo."),
                () -> assertEquals(horario, consultaAgendada.horario(), "Horario da consulta deve ser a mesmo.")
        );
    }

    @Test
    void deveAgendarUmaConsultaComDadosValidosEMedicoDefinidoPeloSistema() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findRandomAvailableMedicoToTheSpecifiedDate(LocalDate.parse(data), LocalTime.parse(horario))).thenReturn(Optional.of(medicoMock));
        when(pacienteMock.getNome()).thenReturn(pacienteNome);
        when(medicoMock.getNome()).thenReturn(medicoNome);

        ConsultaRequestDTO consultaRequestDTO = new ConsultaRequestDTO(pacienteId, 0L, data, horario);
        ConsultaAgendadaDTO consultaAgendada = consultaService.agendar(consultaRequestDTO);


        assertAll("Dados da consulta agendada devem ser consistente com o que foi solicitado.",
                () -> assertEquals(pacienteNome, consultaAgendada.pacienteNome(), "Nome do paciente deve ser consistente com o id."),
                () -> assertEquals(medicoNome, consultaAgendada.medicoNome(), "Nome do medico deve ser consistente com o id."),
                () -> assertEquals(data, consultaAgendada.data(), "Data da consulta deve ser a mesmo."),
                () -> assertEquals(horario, consultaAgendada.horario(), "Horario da consulta deve ser a mesmo.")
        );
    }

    @Test
    void deveLancarExcecaoAoTentarAgendarUmaConsultaSeOPacienteNaoEstiverCadastradosNoSistema() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenThrow(PacienteNaoEncontradoException.class);
        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        assertThrows(PacienteNaoEncontradoException.class, () -> consultaService.agendar(consultaRequestDTO));
    }

    @Test
    void deveLancarExcecaoAoTentarAgendarUmaConsultaSeOMedicoNaoEstiverCadastradosNoSistema() {
        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
        when(medicoRepositoryMock.findById(medicoId)).thenThrow(MedicoNaoEncontradoException.class);
        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
        assertThrows(MedicoNaoEncontradoException.class, () -> consultaService.agendar(consultaRequestDTO));
    }

//    @Test
//    void deveLancarExcecaoQuandoPacienteJaTemConsultaNoMesmoDia() {
//        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
//        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
//        when(consultaRepositoryMock.existsByIdAndData(pacienteId, LocalDate.parse(data))).thenReturn(true);
//        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
//        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO));
//    }

//    @Test
//    void deveLancarExcecaoQuandoMedicoJaTemDozeConsultasNoMesmoDia() {
//        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
//        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
//        when(medicoRepositoryMock.countConsultasByMedicoIdAndData(medicoId, LocalDate.parse(data))).thenReturn(12);
//
//        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
//        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO));
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoHorarioForaDoHorarioDeAtendimentoDoMedico() {
//        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
//        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
//        when(medicoMock.isHorarioDisponivel(LocalTime.parse(horario))).thenReturn(false);
//
//        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
//        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO));
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoMedicoJaTemConsultaNoMesmoHorario() {
//        when(pacienteRepositoryMock.findById(pacienteId)).thenReturn(Optional.of(pacienteMock));
//        when(medicoRepositoryMock.findById(medicoId)).thenReturn(Optional.of(medicoMock));
//        when(medicoRepositoryMock.existsByMedicoIdAndDataAndHorario(medicoId, LocalDate.parse(data), LocalTime.parse(horario))).thenReturn(true);
//
//        ConsultaRequestDTO consultaRequestDTO = getConsultaRequestDTO();
//        assertThrows(ConsultaNaoPodeSerMarcadaException.class, () -> consultaService.agendar(consultaRequestDTO));
//    }


    private ConsultaRequestDTO getConsultaRequestDTO() {
        return new ConsultaRequestDTO(pacienteId, medicoId, data, horario);
    }
}