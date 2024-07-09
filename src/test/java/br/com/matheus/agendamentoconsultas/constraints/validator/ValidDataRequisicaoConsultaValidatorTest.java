package br.com.matheus.agendamentoconsultas.constraints.validator;

import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;

class ValidDataRequisicaoConsultaValidatorTest {

    @InjectMocks
    private ValidDataRequisicaoConsultaValidator validator;

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
    void deveRetornarTrueAoReceberDataComFormatoValido() {
        assertAll("Datas válidas.",
                () -> assertTrue(validator.isValid("2023-01-01", contextMock), "2023-01-01 deve ser válida."),
                () -> assertTrue(validator.isValid("2024-02-29", contextMock), "2024-02-29 deve ser válida."),
                () -> assertTrue(validator.isValid("2024-03-29", contextMock), "2024-02-29 deve ser válida."),
                () -> assertTrue(validator.isValid("2000-04-29", contextMock), "2000-02-29 deve ser válida."),
                () -> assertTrue(validator.isValid("1999-05-15", contextMock), "1999-05-15 deve ser válida."),
                () -> assertTrue(validator.isValid("1999-06-30", contextMock), "2027-06-30 deve ser válida."),
                () -> assertTrue(validator.isValid("2025-07-15", contextMock), "2025-07-15 deve ser válida."),
                () -> assertTrue(validator.isValid("2026-08-20", contextMock), "2026-08-20 deve ser válida."),
                () -> assertTrue(validator.isValid("2024-09-24", contextMock), "2024-09-24 deve ser válida."),
                () -> assertTrue(validator.isValid("2003-10-18", contextMock), "2003-10-18 deve ser válida."),
                () -> assertTrue(validator.isValid("2011-11-11", contextMock), "2011-11-11 deve ser válida."),
                () -> assertTrue(validator.isValid("2023-12-31", contextMock), "2023-12-31 deve ser válida.")
        );
    }

    @Test
    void deveRetornarFalseCasoADataEstejaEmFormatoInvalido() {
        assertAll("Datas inválidas.",
                () -> assertFalse(validator.isValid("01-01-2023", contextMock), "Falhou para a data inválida: 01-01-2023."),
                () -> assertFalse(validator.isValid("2023/01/01", contextMock), "Falhou para a data inválida: 2023/01/01."),
                () -> assertFalse(validator.isValid("2023-1-1", contextMock), "Falhou para a data inválida: 2023-1-1."),
                () -> assertFalse(validator.isValid("2023-01-32", contextMock), "Falhou para a data inválida: 2023-01-32."),
                () -> assertFalse(validator.isValid("abcd-ef-gh", contextMock), "Falhou para a data inválida: abcd-ef-gh."),
                () -> assertFalse(validator.isValid("2023-13-01", contextMock), "Falhou para a data inválida: 2023-13-01."),
                () -> assertFalse(validator.isValid("2023-00-1+0", contextMock), "Falhou para a data inválida: 2023-00-10.")
        );
    }

    @Test
    void deveRetornarFalseCasoOAnoNaoSejaBissexto() {
        assertAll("Datas inválidas de ano não bissexto",
                () -> assertFalse(validator.isValid("2021-02-29", contextMock), "Falhou para a data inválida: 2021-02-29."),
                () -> assertFalse(validator.isValid("2022-02-29", contextMock), "Falhou para a data inválida: 2022-02-29."),
                () -> assertFalse(validator.isValid("2023-02-29", contextMock), "Falhou para a data inválida: 2023-02-29."),
                () -> assertFalse(validator.isValid("2010-02-29", contextMock), "Falhou para a data inválida: 2023-02-29."),
                () -> assertFalse(validator.isValid("2100-02-29", contextMock), "Falhou para a data inválida: 2100-02-29.")
        );
    }

    @Test
    void deveRetornarTrueCasoADataSejaNulaOuVazia() {
        assertAll("Datas vazias ou nulas",
                () -> assertTrue(validator.isValid("", contextMock), "Falhou para data com espaço em branco."),
                () -> assertTrue(validator.isValid("", contextMock), "Falhou para data vazia."),
                () -> assertTrue(validator.isValid(null, contextMock), "Falhou para data nula.")
        );
    }

    @Test
    @SneakyThrows(NullPointerException.class)
    void testGetDiaMaximoDoMesDeDeterminadoAno() {
        Integer janeiro = invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", 1, 2024);
        Integer fevereiroAnoBissexto = invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", 2, 2024);
        Integer fevereiroAnoNaoBissexto = invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", 2, 2023);
        Integer abril = invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", 4, 2024);
        assertAll("Dia máximo de cada mês",
                () -> assertEquals(31, janeiro, "Janeiro deve ter 31 dias."),
                () -> assertEquals(29, fevereiroAnoBissexto, "Fevereiro em ano bissexto deve ter 29 dias."),
                () -> assertEquals(28, fevereiroAnoNaoBissexto, "Fevereiro em ano não bissexto deve ter 28 dias."),
                () -> assertEquals(30, abril, "Abril deve ter 30 dias."),
                () -> assertThrows(IllegalArgumentException.class, () -> invokeMethod(validator, "getDiaMaximoDoMesDeDeterminadoAno", 13, 2024)));
    }
}