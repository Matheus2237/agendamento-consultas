package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;

class ValidDataRequisicaoConsultaValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidDataRequisicaoConsultaValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @ValueSource(strings = {
            "2023-01-01", "2024-02-29", "2024-03-29", "2000-04-29", "1999-05-15",
            "1999-06-30", "2027-06-30", "2025-07-15", "2026-08-20", "2024-09-24",
            "2003-10-18", "2011-11-11", "2023-12-31"
    })
    void deveRetornarTrueAoReceberDataComFormatoValido(String data) {
        assertTrue(validator.isValid(data, contextMock), data.concat(" deve ser válida."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "01-01-2023", "2023/01/01", "2023-1-1", "2023-01-32",
            "abcd-ef-gh", "2023-13-01", "2023-00-1+0"
    })
    void deveRetornarFalseCasoADataEstejaEmFormatoInvalido(String data) {
        assertFalse(validator.isValid(data, contextMock), "Falhou para a data inválida: ".concat(data));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2021-02-29", "2022-02-29", "2023-02-29",
            "2010-02-29", "2100-02-29"
    })
    void deveRetornarFalseCasoOAnoNaoSejaBissexto(String data) {
        assertFalse(validator.isValid(data, contextMock), "Falhou para a data inválida: ".concat(data));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRetornarTrueCasoADataSejaNulaOuVazia(String data) {
        assertTrue(validator.isValid(data, contextMock));
    }

    @ParameterizedTest
    @MethodSource("provisionaDiasEsperadosMesesEAnos")
    @SneakyThrows(NullPointerException.class)
    void deveRetornarOMaiorValorDeDiaDeUmDetermindadoMesEAno(int maiorDiaEsperado, int mes, int ano) {
        Integer maiorDiaDoMes = invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", mes, ano);
        assertEquals(maiorDiaEsperado, maiorDiaDoMes);
    }

    public static Stream<Arguments> provisionaDiasEsperadosMesesEAnos() {
        return Stream.of(
                Arguments.of(31, 1, 2024),
                Arguments.of(28, 2, 2023),
                Arguments.of(29, 2, 2024),
                Arguments.of(31, 3, 2024),
                Arguments.of(30, 4, 2024),
                Arguments.of(31, 5, 2024),
                Arguments.of(30, 6, 2024),
                Arguments.of(31, 7, 2024),
                Arguments.of(31, 8, 2024),
                Arguments.of(30, 9, 2024),
                Arguments.of(31, 10, 2024),
                Arguments.of(30, 11, 2024),
                Arguments.of(31, 12, 2024)
        );
    }

    @Test
    void deveLancarUmaIllegalArgumentExceptionAoTentarObterOMaiorDiaDoMesDeUmMesNaoExistente() {
        int mes = 13;
        int ano = 2024;
        assertThrows(IllegalArgumentException.class,
                () -> invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", mes, ano));
    }
}