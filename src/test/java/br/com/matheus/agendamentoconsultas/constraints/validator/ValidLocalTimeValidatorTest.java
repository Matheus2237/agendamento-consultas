package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidLocalTimeValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidLocalTimeValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @ValueSource(strings = {
            "00:00", "09:30", "12:30", "23:30"
    })
    void deveRetornarTrueAoReceberHorarioComFormatoValido(String horario) {
        assertTrue(validator.isValid(horario, contextMock), horario.concat(" deve ser válido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "24:00", "12:60", "1:00", "09:5",
            "25:00", "23:59:59", "abcd", "12-30"
    })
    void deveRetornarFalseCasoOHorarioEstejaEmFormatoInvalido(String horario) {
        assertFalse(validator.isValid(horario, contextMock), "Horário inválido: ".concat(horario));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "23:59", "00:01", "12:15", "14:37"
    })
    void deveRetornarFalseCasoOHorarioNaoTermineEmPontoOuEm30Minutos(String horario) {
        assertFalse(validator.isValid(horario, contextMock), "Horário não termina em 00 ou 30 minutos.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRetornarTrueCasoOHorarioSejaNuloOuVazio(String horario) {
        assertTrue(validator.isValid(horario, contextMock));
    }
}