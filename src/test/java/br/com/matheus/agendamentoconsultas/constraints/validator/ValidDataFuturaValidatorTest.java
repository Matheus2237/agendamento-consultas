package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidDataFuturaValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidDataFuturaValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @MethodSource("provisionaDatasFuturas")
    void deveRetornarTrueSeADataForFutura(String dataFutura) {
        assertTrue(validator.isValid(dataFutura, contextMock),
                "Deve passar para a data ".concat(dataFutura));
    }

    static Stream<String> provisionaDatasFuturas() {
        return Stream.of(
                LocalDate.now().plusDays(1).toString(),
                LocalDate.now().plusDays(2).toString()
        );
    }

    @ParameterizedTest
    @MethodSource("provisionaDatasPassadas")
    void deveRetornarFalseSeADataForAtualOuPassada(String dataInvalida) {
        assertFalse(validator.isValid(dataInvalida, contextMock),
                "Deve falhar para a data ".concat(dataInvalida));
    }

    static Stream<String> provisionaDatasPassadas() {
        return Stream.of(
                LocalDate.now().minusDays(2).toString(),
                LocalDate.now().minusDays(1).toString(),
                LocalDate.now().toString()
        );
    }
}