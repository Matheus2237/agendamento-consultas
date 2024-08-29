package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidCpfValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidCpfValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @Test
    void deveRetornarTrueCasoForPassadoUmCpfComOFormatoValido() {
        final String cpf = "12345678900";
        assertTrue(validator.isValid(cpf, contextMock), "CPF válido.");
    }

    @ParameterizedTest
    @CsvSource({
            "1234567890a, CPF contém letras.",
            "1234567890@, CPF contém caracteres especiais.",
            "123 4567890, CPF contém espaços.",
            "123456789001, CPF contém mais que 11 dígitos.",
            "1234567890, CPF contém menos que 11 dígitos."
    })
    void deveRetornarFalseCasoOFormatoDoCPFEstiverInconsistente(String cpf, String motivoInconsistencia) {
        assertFalse(validator.isValid(cpf, contextMock), motivoInconsistencia);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRetornarTrueCasoOCPFSejaNuloOuEmBranco(String cpf) {
        assertTrue(validator.isValid(cpf, contextMock));
    }
}