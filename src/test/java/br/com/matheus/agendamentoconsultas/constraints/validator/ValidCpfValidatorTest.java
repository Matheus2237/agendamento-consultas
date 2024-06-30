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

class ValidCpfValidatorTest {

    @InjectMocks
    private ValidCpfValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        mocks.close();
    }

    @Test
    void deveRetornarTrueCasoForPassadoUmCpfComOFormatoValido() {
        final String cpf = "12345678900";
        assertTrue(validator.isValid(cpf, contextMock), "CPF válido.");
    }

    @Test
    void deveRetornarFalseCasoOFormatoDoCPFEstiverInconsistente() {
        assertAll("CPF - Valores inválidos",
                () -> assertFalse(validator.isValid("1234567890a", contextMock), "CPF contém letras."),
                () -> assertFalse(validator.isValid("1234567890@", contextMock), "CPF contém caracteres especiais."),
                () -> assertFalse(validator.isValid("123 4567890", contextMock), "CPF contém espaços."),
                () -> assertFalse(validator.isValid("123456789001", contextMock), "CPF contém mais que 11 dígitos."),
                () -> assertFalse(validator.isValid("1234567890", contextMock), "CPF contém menos que 11 dígitos.")
        );
    }

    @Test
    void deveRetornarTrueCasoOCPFSejaNuloOuEmBranco() {
        assertAll("CPF - Valores brancos",
                () -> assertTrue(validator.isValid(null, contextMock), "CPF nulo."),
                () -> assertTrue(validator.isValid("", contextMock), "CPF vazio."),
                () -> assertTrue(validator.isValid(" ", contextMock), "CPF em branco.")
        );
    }
}