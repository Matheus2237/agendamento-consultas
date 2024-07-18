package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidCrmValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidCrmValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @Test
    void deveRetornarTrueCasoOCRMEstejaValido() {
        assertAll("CRM válido de todas as UFs",
                () -> assertTrue(validator.isValid("AC123456", contextMock), "AC123456 deve ser válido."),
                () -> assertTrue(validator.isValid("AL123456", contextMock), "AL123456 deve ser válido."),
                () -> assertTrue(validator.isValid("AP123456", contextMock), "AP123456 deve ser válido."),
                () -> assertTrue(validator.isValid("AM123456", contextMock), "AM123456 deve ser válido."),
                () -> assertTrue(validator.isValid("BA123456", contextMock), "BA123456 deve ser válido."),
                () -> assertTrue(validator.isValid("CE123456", contextMock), "CE123456 deve ser válido."),
                () -> assertTrue(validator.isValid("DF123456", contextMock), "DF123456 deve ser válido."),
                () -> assertTrue(validator.isValid("ES123456", contextMock), "ES123456 deve ser válido."),
                () -> assertTrue(validator.isValid("GO123456", contextMock), "GO123456 deve ser válido."),
                () -> assertTrue(validator.isValid("MA123456", contextMock), "MA123456 deve ser válido."),
                () -> assertTrue(validator.isValid("MT123456", contextMock), "MT123456 deve ser válido."),
                () -> assertTrue(validator.isValid("MS123456", contextMock), "MS123456 deve ser válido."),
                () -> assertTrue(validator.isValid("MG123456", contextMock), "MG123456 deve ser válido."),
                () -> assertTrue(validator.isValid("PA123456", contextMock), "PA123456 deve ser válido."),
                () -> assertTrue(validator.isValid("PB123456", contextMock), "PB123456 deve ser válido."),
                () -> assertTrue(validator.isValid("PR123456", contextMock), "PR123456 deve ser válido."),
                () -> assertTrue(validator.isValid("PE123456", contextMock), "PE123456 deve ser válido."),
                () -> assertTrue(validator.isValid("PI123456", contextMock), "PI123456 deve ser válido."),
                () -> assertTrue(validator.isValid("RJ123456", contextMock), "RJ123456 deve ser válido."),
                () -> assertTrue(validator.isValid("RN123456", contextMock), "RN123456 deve ser válido."),
                () -> assertTrue(validator.isValid("RS123456", contextMock), "RS123456 deve ser válido."),
                () -> assertTrue(validator.isValid("RO123456", contextMock), "RO123456 deve ser válido."),
                () -> assertTrue(validator.isValid("RR123456", contextMock), "RR123456 deve ser válido."),
                () -> assertTrue(validator.isValid("SC123456", contextMock), "SC123456 deve ser válido."),
                () -> assertTrue(validator.isValid("SP123456", contextMock), "SP123456 deve ser válido."),
                () -> assertTrue(validator.isValid("SE123456", contextMock), "SE123456 deve ser válido."),
                () -> assertTrue(validator.isValid("TO123456", contextMock), "TO123456 deve ser válido.")
        );
    }

    @Test
    void deveRetornarTrueCasoOCRMPossuaUFEDigitosValidos() {
        assertAll("CRM válido.",
                () -> assertTrue(validator.isValid("SP123456", contextMock), "SP123456 deve ser válido."),
                () -> assertTrue(validator.isValid("RJ654321", contextMock), "RJ654321 deve ser válido."),
                () -> assertTrue(validator.isValid("MG000001", contextMock), "MG000001 deve ser válido."),
                () -> assertTrue(validator.isValid("RS999999", contextMock), "RS999999 deve ser válido.")
        );
    }

    @Test
    void deveRetornarFalseCasoOFormatoDoCPFEstiverInconsistente() {
        assertAll("CRM inválido.",
                () -> assertFalse(validator.isValid("XX123456", contextMock), "XX123456 deve ser inválido."),
                () -> assertFalse(validator.isValid("SP12345", contextMock), "SP12345 deve ser inválido."),
                () -> assertFalse(validator.isValid("SP1234567", contextMock), "SP1234567 deve ser inválido."),
                () -> assertFalse(validator.isValid("123456SP", contextMock), "123456SP deve ser inválido."),
                () -> assertFalse(validator.isValid("SP1234A6", contextMock), "SP1234A6 deve ser inválido."),
                () -> assertFalse(validator.isValid("SP12 3456", contextMock), "SP12 3456 deve ser inválido."),
                () -> assertFalse(validator.isValid("SP 123456", contextMock), "SP 123456 deve ser inválido.")
        );
    }

    @Test
    void deveRetornarTrueCasoOCRMSejaNuloOuVazio() {
        assertAll("CRM nulo e vazio.",
                () -> assertTrue(validator.isValid(null, contextMock), "Nulo deve ser válido."),
                () -> assertTrue(validator.isValid("", contextMock), "Vazio deve ser válido."),
                () -> assertTrue(validator.isValid("   ", contextMock), "Espaço em branco deve ser válido.")
        );
    }

    @Test
    void deveRetornarFalseCasoOCRMTenhaAUFEmLowerCase() {
        assertAll("CRM com UF em minúsculas.",
                () -> assertFalse(validator.isValid("sp123456", contextMock), "sp123456 deve ser inválido."),
                () -> assertFalse(validator.isValid("rj654321", contextMock), "rj654321 deve ser inválido."),
                () -> assertFalse(validator.isValid("mg000001", contextMock), "mg000001 deve ser inválido."),
                () -> assertFalse(validator.isValid("rs999999", contextMock), "rs999999 deve ser inválido.")
        );
    }
}