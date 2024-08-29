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

class ValidDiaDaSemanaValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidDiaDaSemanaValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @ValueSource(strings = {
            "DOMINGO", "SEGUNDA", "TERCA", "QUARTA",
            "QUINTA", "SEXTA", "SABADO"
    })
    void deveRetornarTrueAoReceberUmDiaDaSemanaValidoEmUpperCase(String diaDaSemana) {
        assertTrue(validator.isValid(diaDaSemana, contextMock), diaDaSemana.concat(" deve ser válido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "domingo", "segunda", "terca", "quarta",
            "quinta", "sexta", "sabado"
    })
    void deveRetornarFalseAoReceberUmDiaDaSemanaValidoEmLowerCase(String diaDaSemana) {
        assertFalse(validator.isValid(diaDaSemana, contextMock), diaDaSemana.concat(" deve ser inválido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "DOMINGOA", "SEGUNDAA", "TERCAA", "QUARTAA",
            "QUINTAA", "SEXTAA", "SABADOA", "DOM",
            "SEG", "TER", "QUA", "QUI", "SEX",
            "SAB", "DO", "SE"
    })
    void deveRetornarFalseAoReceberUmDiaDaSemanaInvalido(String diaDaSemana) {
        assertFalse(validator.isValid(diaDaSemana, contextMock), diaDaSemana.concat(" deve ser inválido."));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRetornarTrueAoReceberNuloOuVazio(String diaDaSemana) {
        assertTrue(validator.isValid(diaDaSemana, contextMock));
    }
}