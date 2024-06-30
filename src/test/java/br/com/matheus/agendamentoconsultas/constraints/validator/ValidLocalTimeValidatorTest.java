package br.com.matheus.agendamentoconsultas.constraints.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidLocalTimeValidatorTest {

    @InjectMocks
    private ValidLocalTimeValidator validator;

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
    void deveRetornarTrueAoReceberHorarioComFormatoValido() {
        assertAll("Horários válidos.",
                () -> assertTrue(validator.isValid("00:00", contextMock), "00:00 deve ser válido."),
                () -> assertTrue(validator.isValid("09:30", contextMock), "09:30 deve ser válido."),
                () -> assertTrue(validator.isValid("12:30", contextMock), "12:30 deve ser válido."),
                () -> assertTrue(validator.isValid("23:30", contextMock), "23:30 deve ser válido.")
        );
    }

    @Test
    void deveRetornarFalseCasoOHorarioEstejaEmFormatoInvalido() {
        assertAll("Horários inválidos.",
                () -> assertFalse(validator.isValid("24:00", contextMock), "Falhou para o horário inválido: 24:00."),
                () -> assertFalse(validator.isValid("12:60", contextMock), "Falhou para o horário inválido: 12:60."),
                () -> assertFalse(validator.isValid("1:00", contextMock), "Falhou para o horário inválido: 1:00."),
                () -> assertFalse(validator.isValid("09:5", contextMock), "Falhou para o horário inválido: 09:5."),
                () -> assertFalse(validator.isValid("25:00", contextMock), "Falhou para o horário inválido: 25:00."),
                () -> assertFalse(validator.isValid("23:59:59", contextMock), "Falhou para o horário inválido: 23:59:59."),
                () -> assertFalse(validator.isValid("abcd", contextMock), "Falhou para o horário inválido: abcd."),
                () -> assertFalse(validator.isValid("12-30", contextMock), "Falhou para o horário inválido: 12-30.")
        );
    }

    @Test
    void deveRetornarTrueCasoOHorarioSejaNuloOuVazio() {
        assertAll("Horários vazios ou nulos",
                () -> assertTrue(validator.isValid("", contextMock), "Falhou para horário com espaço em branco."),
                () -> assertTrue(validator.isValid("", contextMock), "Falhou para horário vazio."),
                () -> assertTrue(validator.isValid(null, contextMock), "Falhou para horário nulo.")
        );
    }

    @Test
    void deveRetornarFalseCasoOHorarioNaoTermineEmPontoOuEm30Minutos() {
        assertAll("Horários inválidos.",
                () -> assertFalse(validator.isValid("23:59", contextMock), "Falhou para o horário inválido: 23:59."),
                () -> assertFalse(validator.isValid("00:01", contextMock), "Falhou para o horário inválido: 00:01."),
                () -> assertFalse(validator.isValid("12:15", contextMock), "Falhou para o horário inválido: 12:15."),
                () -> assertFalse(validator.isValid("14:37", contextMock), "Falhou para o horário inválido: 14:37.")
        );
    }
}