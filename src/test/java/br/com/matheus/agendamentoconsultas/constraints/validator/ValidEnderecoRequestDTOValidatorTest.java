package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.controller.dto.EnderecoRequestDTO;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ValidEnderecoRequestDTOValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidEnderecoRequestDTOValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @Test
    void deveRetornarTrueQuandoUmEnderecoTiverUFECEPInconsistentes() {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                "Av. Bandeirantes", "21, andar 4, apartamento 42",
                "Jardim Planalto", "São Paulo", "SP", "12345678");
        assertTrue(validator.isValid(endereco, contextMock), "Endereço com uf e cep inválidos.");
    }

    @Test
    void deveRetornarFalseQuandoUmEnderecoTiverUFECEPInconsistentes() {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                "Av. Bandeirantes", "21, andar 4, apartamento 42",
                "Jardim Planalto", "São Paulo", "XX", "1234567");
        assertFalse(validator.isValid(endereco, contextMock), "Endereço com uf e cep inválidos.");
    }

    @Test
    void deveRetornarFalseQuandoAUFForInconsistente() {
        assertAll("UF - Valores inválidos.",
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "XX", "12345678");
                    assertFalse(validator.isValid(endereco, contextMock), "UF inexistente.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "S1", "12345678");
                    assertFalse(validator.isValid(endereco, contextMock), "UF com valor numérico.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "12", "12345678");
                    assertFalse(validator.isValid(endereco, contextMock), "UF numérico.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "S!", "12345678");
                    assertFalse(validator.isValid(endereco, contextMock), "UF com caracteres especiais.");
                }
        );
    }

    @Test
    void deveRetornarFalseQuandoOCepForInconsistente() {
        assertAll("CEP - Valores inválidos.",
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "1234567");
                    assertFalse(validator.isValid(endereco, contextMock), "CEP menor.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "123456789");
                    assertFalse(validator.isValid(endereco, contextMock), "CEP maior.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "12345-678");
                    assertFalse(validator.isValid(endereco, contextMock), "CEP com caracteres especiais.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "12345A78");
                    assertFalse(validator.isValid(endereco, contextMock), "CEP com caracteres não numéricos.");
                }
        );
    }

    @Test
    void deveRetornarTrueCasoOEnderecoSejaNuloOuPossuaValoresEmBranco() {
        assertAll("EnderecoCadastroDTO - Valores nulos ou vazios.",
                () -> assertTrue(validator.isValid(null, contextMock), "Logradouro nulo."),
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            null, "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Logradouro nulo.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", null,
                            "Jardim Planalto", "São Paulo", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Número nulo.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            null, "São Paulo", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Bairro nulo.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", null, "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Cidade nula.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", null, "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "UF nula.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", null);
                    assertTrue(validator.isValid(endereco, contextMock), "CEP nulo.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Logradouro vazio.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "",
                            "Jardim Planalto", "São Paulo", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Número vazio.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "", "São Paulo", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Bairro vazio.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "", "SP", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "Cidade vazia.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "", "12345678");
                    assertTrue(validator.isValid(endereco, contextMock), "UF vazio.");
                },
                () -> {
                    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                            "Av. Bandeirantes", "21, andar 4, apartamento 42",
                            "Jardim Planalto", "São Paulo", "SP", "");
                    assertTrue(validator.isValid(endereco, contextMock), "CEP vazio.");
                }
        );
    }
}