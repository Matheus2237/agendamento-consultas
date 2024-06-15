package br.com.matheus.agendamentoconsultas.constraints.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidDiaDaSemanaValidatorTest {

    @InjectMocks
    private ValidDiaDaSemanaValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void deveRetornarTrueAoReceberUmDiaDaSemanaValidoEmUpperCase() {
        assertAll("Dias da semana válidos.",
                () -> assertTrue(validator.isValid("DOMINGO", contextMock), "DOMINGO deve ser válido."),
                () -> assertTrue(validator.isValid("SEGUNDA", contextMock), "SEGUNDA deve ser válido."),
                () -> assertTrue(validator.isValid("TERCA", contextMock), "TERCA deve ser válido."),
                () -> assertTrue(validator.isValid("QUARTA", contextMock), "QUARTA deve ser válido."),
                () -> assertTrue(validator.isValid("QUINTA", contextMock), "QUINTA deve ser válido."),
                () -> assertTrue(validator.isValid("SEXTA", contextMock), "SEXTA deve ser válido."),
                () -> assertTrue(validator.isValid("SABADO", contextMock), "SABADO deve ser válido.")
        );
    }

    @Test
    void deveRetornarFalseAoReceberUmDiaDaSemanaValidoEmLowerCase() {
        assertAll("Dias da semana em minúsculas.",
                () -> assertFalse(validator.isValid("domingo", contextMock), "domingo deve ser inválido."),
                () -> assertFalse(validator.isValid("segunda", contextMock), "segunda deve ser inválido."),
                () -> assertFalse(validator.isValid("terca", contextMock), "terca deve ser inválido."),
                () -> assertFalse(validator.isValid("quarta", contextMock), "quarta deve ser inválido."),
                () -> assertFalse(validator.isValid("quinta", contextMock), "quinta deve ser inválido."),
                () -> assertFalse(validator.isValid("sexta", contextMock), "sexta deve ser inválido."),
                () -> assertFalse(validator.isValid("sabado", contextMock), "sabado deve ser inválido.")
        );
    }

    @Test
    void deveRetornarFalseAoReceberUmDiaDaSemanaInvalido() {
        assertAll("Dias da semana inválidos.",
                () -> assertFalse(validator.isValid("DOMINGOA", contextMock), "DOMINGOA deve ser inválido."),
                () -> assertFalse(validator.isValid("SEGUNDAA", contextMock), "SEGUNDAA deve ser inválido."),
                () -> assertFalse(validator.isValid("TERCAA", contextMock), "TERCAA deve ser inválido."),
                () -> assertFalse(validator.isValid("QUARTAA", contextMock), "QUARTAA deve ser inválido."),
                () -> assertFalse(validator.isValid("QUINTAA", contextMock), "QUINTAA deve ser inválido."),
                () -> assertFalse(validator.isValid("SEXTAA", contextMock), "SEXTAA deve ser inválido."),
                () -> assertFalse(validator.isValid("SABADOA", contextMock), "SABADOA deve ser inválido."),
                () -> assertFalse(validator.isValid("DOM", contextMock), "DOM deve ser inválido."),
                () -> assertFalse(validator.isValid("SEG", contextMock), "SEG deve ser inválido."),
                () -> assertFalse(validator.isValid("TER", contextMock), "TER deve ser inválido."),
                () -> assertFalse(validator.isValid("QUA", contextMock), "QUA deve ser inválido."),
                () -> assertFalse(validator.isValid("QUI", contextMock), "QUI deve ser inválido."),
                () -> assertFalse(validator.isValid("SEX", contextMock), "SEX deve ser inválido."),
                () -> assertFalse(validator.isValid("SAB", contextMock), "SAB deve ser inválido."),
                () -> assertFalse(validator.isValid("DO", contextMock), "DO deve ser inválido."),
                () -> assertFalse(validator.isValid("SE", contextMock), "SE deve ser inválido.")
        );
    }

    @Test
    void deveRetornarTrueAoReceberNuloOuVazio() {
        assertAll("Dias da semana nulos ou vazios.",
                () -> assertTrue(validator.isValid(null, contextMock), "Nulo deve ser válido."),
                () -> assertTrue(validator.isValid("", contextMock), "Vazio deve ser válido."),
                () -> assertTrue(validator.isValid("   ", contextMock), "Espaço em branco deve ser válido.")
        );
    }
}