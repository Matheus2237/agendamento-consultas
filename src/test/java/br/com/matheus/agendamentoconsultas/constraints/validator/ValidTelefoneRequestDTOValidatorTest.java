package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.controller.dto.TelefoneRequestDTO;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidTelefoneRequestDTOValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidTelefoneRequestDTOValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @Test
    void deveRetornarTrueQuandoOFormatoDoTelefoneEstiverValido() {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO("11", "987654321");
        assertTrue(validator.isValid(telefoneRequestDTO, contextMock), "DDD e número válidos.");
    }

    @ParameterizedTest
    @CsvSource({
            "'1a', '987654321'",
            "'11', '98765abcd'",
            "'11!', '98765@#$%'",
            "'11', '98765 4321'",
            "'1 1', '987654321'",
            "'1', '987654321'",
            "'111', '987654321'",
            "'11', '98765432'",
            "'11', '9876543210'"
    })
    void deveRetornarFalseQuandoOFormatoDoTelefoneEstiverInconsistente(String ddd, String numero) {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(ddd, numero);
        assertFalse(validator.isValid(telefoneRequestDTO, contextMock));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null",
            "null, '987654321'",
            "'11', null",
            "null, null",
            "'', '987654321'",
            "'11', ''",
            "'', ''"
    }, nullValues = "null")
    void deveRetornarTrueCasoOTelefonePossuaValoresEmBranco(String ddd, String numero) {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(ddd, numero);
        assertTrue(validator.isValid(telefoneRequestDTO, contextMock));
    }

    @ParameterizedTest
    @NullSource
    void deveRetornarTrueCaseOTelefoneSejaNulo(TelefoneRequestDTO telefoneRequestDTO) {
        assertTrue(validator.isValid(telefoneRequestDTO, contextMock));
    }
}