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
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;
import org.junitpioneer.jupiter.cartesian.CartesianTest.MethodFactory;
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

    @CartesianTest
    @MethodFactory("provisionaNumerosEDDDsInvalidos")
    void deveRetornarFalseQuandoOFormatoDoTelefoneEstiverInconsistente(String ddd, String numero) {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(ddd, numero);
        assertFalse(validator.isValid(telefoneRequestDTO, contextMock));
    }

    @SuppressWarnings("unused")
    static ArgumentSets provisionaNumerosEDDDsInvalidos() {
        return ArgumentSets
                .argumentsForFirstParameter("1a", "11!", "1 1", "1", "111")
                .argumentsForNextParameter("98765abcd", "98765@#$%", "9876543210", "98765 4321", "98765432");
    }

    @CartesianTest
    @MethodFactory("provisionaNumerosEDDDsNulosBrancosEValidos")
    void deveRetornarTrueCasoOTelefonePossuaValoresEmBranco(String ddd, String numero) {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(ddd, numero);
        assertTrue(validator.isValid(telefoneRequestDTO, contextMock));
    }

    @SuppressWarnings("unused")
    static ArgumentSets provisionaNumerosEDDDsNulosBrancosEValidos() {
        return ArgumentSets
                .argumentsForFirstParameter(null, "", " ", "11")
                .argumentsForNextParameter(null, "", " ", "987654321");
    }

    @ParameterizedTest
    @NullSource
    void deveRetornarTrueCaseOTelefoneSejaNulo(TelefoneRequestDTO telefoneRequestDTO) {
        assertTrue(validator.isValid(telefoneRequestDTO, contextMock));
    }
}