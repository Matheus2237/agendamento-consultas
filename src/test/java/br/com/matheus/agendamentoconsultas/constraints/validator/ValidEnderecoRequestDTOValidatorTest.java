package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.controller.dto.EnderecoRequestDTO;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ValidEnderecoRequestDTOValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidEnderecoRequestDTOValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @CsvSource({
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '12345678'",
            "'Rua Fictícia', '100, bloco B, apartamento 202', 'Centro', 'Rio de Janeiro', 'RJ', '87654321'",
            "'Av. Brasil', '1500, sala 10', 'Comercial', 'Curitiba', 'PR', '11223344'"
    })
    void deveRetornarTrueQuandoUmEnderecoForConsistente(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(logradouro, numero, bairro, cidade, uf, cep);
        assertTrue(validator.isValid(endereco, contextMock), "Endereco válido.");
    }

    @ParameterizedTest
    @CsvSource({
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'XX', '1234567'",
            "'Rua Fictícia', '100, bloco B, apartamento 202', 'Centro', 'Rio de Janeiro', 'YY', '8765432'",
            "'Av. Brasil', '1500, sala 10', 'Comercial', 'Curitiba', 'ZZ', '1122334'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'XX', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'S1', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', '12', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'S!', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '1234567'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '123456789'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '12345-678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '12345A78'"
    })
    void deveRetornarFalseQuandoUmEnderecoTiverUFOuCEPInconsistentes(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(logradouro, numero, bairro, cidade, uf, cep);
        assertFalse(validator.isValid(endereco, contextMock));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '12345678'",
            "'Av. Bandeirantes', null, 'Jardim Planalto', 'São Paulo', 'SP', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', null, 'São Paulo', 'SP', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', null, 'SP', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', null, '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', null",
            "'', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', '12345678'",
            "'Av. Bandeirantes', '', 'Jardim Planalto', 'São Paulo', 'SP', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', '', 'São Paulo', 'SP', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', '', 'SP', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', '', '12345678'",
            "'Av. Bandeirantes', '21, andar 4, apartamento 42', 'Jardim Planalto', 'São Paulo', 'SP', ''"
    }, nullValues = "null")
    void deveRetornarTrueCasoOEnderecoSejaNuloOuPossuaValoresEmBranco(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(logradouro, numero, bairro, cidade, uf, cep);
        assertTrue(validator.isValid(endereco, contextMock));
    }
}