package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidEspecializacaoValidatorTest extends MockedUnitTest {

    @InjectMocks
    private ValidEspecializacaoValidator validator;

    @Mock
    private ConstraintValidatorContext contextMock;

    @ParameterizedTest
    @ValueSource(strings = {
            "IMUNOLOGIA", "ANESTESIOLOGIA", "ANGIOLOGIA", "CARDIOLOGIA",
            "CLINICA", "COLOPROCTOLOGIA", "DERMATOLOGIA", "ENDOCRINOLOGIA",
            "GASTROENTEROLOGIA", "GENETICA_MEDICA", "GERIATRIA", "GINECOLOGIA",
            "HEMATOLOGIA", "INFECTOLOGIA", "MASTOLOGIA", "ESPORTIVA",
            "PERICIA", "NEFROLOGIA", "NEUROLOGIA", "OFTALMOLOGIA",
            "ONCOLOGIA", "ORTOPEDIA", "OTORRINOLARINGOLOGIA", "PATOLOGIA",
            "PEDIATRIA", "PNEUMOLOGIA", "PSIQUIATRIA", "REUMATOLOGIA",
            "UROLOGIA"
    })
    void deveRetornarTrueAoReceberUmaEspecializacaoValidaEmUpperCase(String especializacao) {
        assertTrue(validator.isValid(especializacao, contextMock), especializacao.concat(" deve ser válido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "imunologia", "anestesiologia", "angiologia", "cardiologia",
            "clinica", "coloproctologia", "dermatologia", "endocrinologia",
            "gastroenterologia", "genetica_medica", "geriatria", "ginecologia",
            "hematologia", "infectologia", "mastologia", "esportiva",
            "pericia", "nefrologia", "neurologia", "oftalmologia",
            "oncologia", "ortopedia", "otorrinolaringologia", "patologia",
            "pediatria", "pneumologia", "psiquiatria", "reumatologia",
            "urologia", "INVALIDO", "ANESTESIOLOGIAA", "Cardiologia", "GINECOLOGIAA",
            "ortopedia", "PEDIATRA", "REUMATOLOGIAA"
    })
    void deveRetornarFalseAoReceberUmaEspecializacaoValidaEmLowerCase(String especializacao) {
        assertFalse(validator.isValid(especializacao, contextMock), especializacao.concat(" deve ser inválido."));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRetornarTrueAoReceberNuloOuVazio(String especializacao) {
        assertTrue(validator.isValid(especializacao, contextMock));
    }
}