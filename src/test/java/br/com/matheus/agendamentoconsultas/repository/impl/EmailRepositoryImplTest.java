package br.com.matheus.agendamentoconsultas.repository.impl;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EmailRepositoryImplTest extends MockedUnitTest {

    @InjectMocks
    private EmailRepositoryImpl emailRepository;

    @Mock
    private PacienteRepository pacienteRepositoryMock;

    @Mock
    private JpaRepository<Object, Long> respositoryForaDeEscopoMock;

    @Mock
    private MedicoRepository medicoRepositoryMock;

    private static final String EMAIL = "exemplo@email.com";

    @Test
    void deveRetornarTrueSeOEmailExistirNaTabelaDeMedicos() {
        when(medicoRepositoryMock.existsByEmailValue(EMAIL)).thenReturn(true);
        boolean isValid = emailRepository.existsByEmail(EMAIL, medicoRepositoryMock);
        assertTrue(isValid, "Deve retornar verdadeiro quando o email não está cadastrado para um médico.");
    }

    @Test
    void deveRetornarFalseSeOEmailExistirNaTabelaDeMedicos() {
        when(medicoRepositoryMock.existsByEmailValue(EMAIL)).thenReturn(false);
        boolean isValid = emailRepository.existsByEmail(EMAIL, medicoRepositoryMock);
        assertFalse(isValid, "Deve retornar falso quando o email já está cadastrado para um médico.");
    }

    @Test
    void deveRetornarTrueSeOEmailExistirNaTabelaDePacientes() {
        when(pacienteRepositoryMock.existsByEmailValue(EMAIL)).thenReturn(true);
        boolean isValid = emailRepository.existsByEmail(EMAIL, pacienteRepositoryMock);
        assertTrue(isValid, "Deve retornar verdadeiro quando o email não está cadastrado para um paciente.");
    }

    @Test
    void deveRetornarFalseSeOEmailExistirNaTabelaDePacientes() {
        when(pacienteRepositoryMock.existsByEmailValue(EMAIL)).thenReturn(false);
        boolean isValid = emailRepository.existsByEmail(EMAIL, pacienteRepositoryMock);
        assertFalse(isValid, "Deve retornar falso quando o email já está cadastrado para um paciente.");
    }

    @Test
    void deveLancarUmaIllegalStateExceptionAoPassarUmRepositorioQueNaoSejaEscopoDoProjeto() {
        assertThrows(IllegalStateException.class,
                () -> emailRepository.existsByEmail(EMAIL, respositoryForaDeEscopoMock),
                "Deve lancar uma Illegal State Exception para repositorios nao permitidos.");
    }
}