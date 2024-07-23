package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.controller.dto.EnderecoRequestDTO;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;
import org.junitpioneer.jupiter.cartesian.CartesianTest.MethodFactory;
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

    @CartesianTest
    @MethodFactory("provisionaCEPOuUFInconsistentes")
    void deveRetornarFalseQuandoUmEnderecoTiverUFOuCEPInconsistentes(String uf, String cep) {
        String logradouro = "Av. Bandeirantes";
        String numero = "21, andar 4, apartamento 42";
        String bairro = "Jardim Planalto";
        String cidade = "São Paulo";
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(logradouro, numero, bairro, cidade, uf, cep);
        assertFalse(validator.isValid(endereco, contextMock));
    }

    @SuppressWarnings("unused")
    static ArgumentSets provisionaCEPOuUFInconsistentes() {
        return ArgumentSets
                .argumentsForFirstParameter("XX", "YY", "ZZ", "S1", "12", "S!", "MZ")
                .argumentsForNextParameter("1234567", "123456789", "12345A78", "12345-678");
    }

    @CartesianTest
    @MethodFactory("provisionaDadosNulosOuBrancosParaOEndereco")
    void deveRetornarTrueCasoOEnderecoSejaNuloOuPossuaValoresEmBranco(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO(logradouro, numero, bairro, cidade, uf, cep);
        assertTrue(validator.isValid(endereco, contextMock));
    }

    @SuppressWarnings("unused")
    static ArgumentSets provisionaDadosNulosOuBrancosParaOEndereco() {
        return ArgumentSets
                .argumentsForFirstParameter(null, "", " ", "Av. Bandeirantes")
                .argumentsForNextParameter(null, "", " ", "21, andar 4, apartamento 42", "43")
                .argumentsForNextParameter(null, "", " ", "Jardim Planalto")
                .argumentsForNextParameter(null, "", " ", "São Paulo")
                .argumentsForNextParameter(null, "", " ", "SP")
                .argumentsForNextParameter(null, "", " ", "12345678");
    }
}