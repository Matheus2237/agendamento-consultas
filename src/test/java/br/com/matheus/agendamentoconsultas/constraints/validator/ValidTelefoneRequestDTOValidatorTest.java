package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.controller.dto.TelefoneRequestDTO;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidTelefoneRequestDTOValidatorTest {

    @InjectMocks
    private ValidTelefoneRequestDTOValidator validator;

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
    void deveRetornarTrueQuandoOFormatoDoTelefoneEstiverValido() {
        assertTrue(validator.isValid(new TelefoneRequestDTO("11", "987654321"), contextMock), "DDD e número válidos.");
    }

    @Test
    void deveRetornarFalseQuandoOFormatoDoTelefoneEstiverInconsistente() {
        assertAll("TelefoneCadastroDTO - Números inválidos.",
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("1a", "987654321"), contextMock), "DDD contém letras."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("11", "98765abcd"), contextMock), "Número contém letras."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("11!", "98765@#$%"), contextMock), "DDD e número contém caracteres especiais."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("11", "98765 4321"), contextMock), "Número contém espaçoes."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("1 1", "987654321"), contextMock), "DDD contém espaços."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("1", "987654321"), contextMock), "DDD tem menos que dois dígitos."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("111", "987654321"), contextMock), "DDD tem mais que dois dígitos."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("11", "98765432"), contextMock), "Número tem menos que nove dígitos."),
                () -> assertFalse(validator.isValid(new TelefoneRequestDTO("11", "9876543210"), contextMock), "Número tem mais que nove dígitos.")
        );
    }

    @Test
    void deveRetornarTrueCasoOTelefoneSejaNuloOuPossuaValoresEmBranco() {
        assertAll("TelefoneCadastroDTO - Valores nulos e vazios.",
                () -> assertTrue(validator.isValid(null, contextMock), "Telefone inexistente."),
                () -> assertTrue(validator.isValid(new TelefoneRequestDTO(null, "987654321"), contextMock), "DDD inexistente."),
                () -> assertTrue(validator.isValid(new TelefoneRequestDTO("11", null), contextMock), "Número inexistente."),
                () -> assertTrue(validator.isValid(new TelefoneRequestDTO(null, null), contextMock), "DDD e número inexistentes."),
                () -> assertTrue(validator.isValid(new TelefoneRequestDTO("", "987654321"), contextMock), "DDD vazio."),
                () -> assertTrue(validator.isValid(new TelefoneRequestDTO("11", ""), contextMock), "Número vazio."),
                () -> assertTrue(validator.isValid(new TelefoneRequestDTO("", ""), contextMock), "DDD e número vazios.")
        );
    }
}