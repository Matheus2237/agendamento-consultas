package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.base.clock.DataFixaConfig;
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
import org.springframework.context.annotation.Import;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ValidDataFuturaValidatorTest extends MockedUnitTest {

    private static final LocalDate MOCKED_TODAY = LocalDate.of(2024, 7, 21);

    @InjectMocks
    private ValidDataFuturaValidator validator;

    @Mock
    private Clock clock;

    @Mock
    private ConstraintValidatorContext contextMock;

    @BeforeEach
    public void setup() {
        ZoneId zoneId = ZoneId.systemDefault();
        when(clock.instant()).thenReturn(MOCKED_TODAY.atStartOfDay(zoneId).toInstant());
        when(clock.getZone()).thenReturn(zoneId);
    }

    @ParameterizedTest
    @MethodSource("provisionaDatasFuturas")
    void deveRetornarTrueSeADataForFutura(String dataFutura) {
        assertTrue(validator.isValid(dataFutura, contextMock),
                "Deve passar para a data ".concat(dataFutura));
    }

    static Stream<String> provisionaDatasFuturas() {
        return Stream.of(
                MOCKED_TODAY.plusDays(1).toString(),
                MOCKED_TODAY.plusDays(2).toString()
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
                MOCKED_TODAY.minusDays(2).toString(),
                MOCKED_TODAY.minusDays(1).toString(),
                MOCKED_TODAY.toString()
        );
    }
}