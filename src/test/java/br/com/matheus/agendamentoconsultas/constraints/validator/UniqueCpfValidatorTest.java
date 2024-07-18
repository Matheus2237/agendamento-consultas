package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UniqueCpfValidatorTest extends MockedUnitTest {

    @InjectMocks
    private UniqueCpfValidator validator;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private ConstraintValidatorContext context;

    private static final String CPF = "12345678900";

    @Test
    void deveRetornarVerdadeiroAoPassarUmCpfUnicoNoSistema() {
        when(pacienteRepository.existsByCpfValue(CPF)).thenReturn(false);
        boolean isValid = validator.isValid(CPF, context);
        assertTrue(isValid, "Deve retornar verdadeiro quando não existir esse cpf no sistema.");
    }

    @Test
    void deveRetornarFalsoAoPassarUmCpfJaExistenteNoSistema() {
        when(pacienteRepository.existsByCpfValue(CPF)).thenReturn(true);
        boolean isValid = validator.isValid(CPF, context);
        assertFalse(isValid, "Deve retornar falso ao existir um cpf no sistema.");
    }
}