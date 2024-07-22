package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidCrmValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidCrmValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @ValueSource(strings = {
            "AC123456", "AL123456", "AP123456", "AM123456", "BA123456",
            "CE123456", "DF123456", "ES123456", "GO123456", "MA123456",
            "MT123456", "MS123456", "MG123456", "PA123456", "PB123456",
            "PR123456", "PE123456", "PI123456", "RJ123456", "RN123456",
            "RS123456", "RO123456", "RR123456", "SC123456", "SP123456",
            "SE123456", "TO123456"
    })
    void deveRetornarTrueCasoOCRMEstejaValido(String crm) {
        assertTrue(validator.isValid(crm, contextMock), crm.concat(" deve ser válido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "SP123456", "RJ654321", "MG000001", "RS999999"
    })
    void deveRetornarTrueCasoOCRMPossuaUFEDigitosValidos(String crm) {
        assertTrue(validator.isValid(crm, contextMock), crm.concat(" deve ser válido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "XX123456", "SP12345", "SP1234567", "123456SP",
            "SP1234A6", "SP12 3456", "SP 123456"
    })
    void deveRetornarFalseCasoOFormatoDoCPFEstiverInconsistente(String crm) {
        assertFalse(validator.isValid(crm, contextMock), crm.concat(" deve ser inválido."));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRetornarTrueCasoOCRMSejaNuloOuVazio(String crm) {
        assertTrue(validator.isValid(crm, contextMock));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "sp123456", "rj654321", "mg000001", "rs999999"
    })
    void deveRetornarFalseCasoOCRMTenhaAUFEmLowerCase(String crm) {
        assertFalse(validator.isValid(crm, contextMock), crm.concat(" deve ser inválido."));
    }
}