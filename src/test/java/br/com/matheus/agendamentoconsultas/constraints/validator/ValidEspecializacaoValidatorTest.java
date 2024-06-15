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

class ValidEspecializacaoValidatorTest {

    @InjectMocks
    private ValidEspecializacaoValidator validator;

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
    void deveRetornarTrueAoReceberUmaEspecializacaoValidaEmUpperCase() {
        assertAll("Especializações válidas.",
                () -> assertTrue(validator.isValid("IMUNOLOGIA", contextMock), "IMUNOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("ANESTESIOLOGIA", contextMock), "ANESTESIOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("ANGIOLOGIA", contextMock), "ANGIOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("CARDIOLOGIA", contextMock), "CARDIOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("CLINICA", contextMock), "CLINICA deve ser válido."),
                () -> assertTrue(validator.isValid("COLOPROCTOLOGIA", contextMock), "COLOPROCTOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("DERMATOLOGIA", contextMock), "DERMATOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("ENDOCRINOLOGIA", contextMock), "ENDOCRINOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("GASTROENTEROLOGIA", contextMock), "GASTROENTEROLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("GENETICA_MEDICA", contextMock), "GENETICA_MEDICA deve ser válido."),
                () -> assertTrue(validator.isValid("GERIATRIA", contextMock), "GERIATRIA deve ser válido."),
                () -> assertTrue(validator.isValid("GINECOLOGIA", contextMock), "GINECOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("HEMATOLOGIA", contextMock), "HEMATOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("INFECTOLOGIA", contextMock), "INFECTOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("MASTOLOGIA", contextMock), "MASTOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("ESPORTIVA", contextMock), "ESPORTIVA deve ser válido."),
                () -> assertTrue(validator.isValid("PERICIA", contextMock), "PERICIA deve ser válido."),
                () -> assertTrue(validator.isValid("NEFROLOGIA", contextMock), "NEFROLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("NEUROLOGIA", contextMock), "NEUROLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("OFTALMOLOGIA", contextMock), "OFTALMOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("ONCOLOGIA", contextMock), "ONCOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("ORTOPEDIA", contextMock), "ORTOPEDIA deve ser válido."),
                () -> assertTrue(validator.isValid("OTORRINOLARINGOLOGIA", contextMock), "OTORRINOLARINGOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("PATOLOGIA", contextMock), "PATOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("PEDIATRIA", contextMock), "PEDIATRIA deve ser válido."),
                () -> assertTrue(validator.isValid("PNEUMOLOGIA", contextMock), "PNEUMOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("PSIQUIATRIA", contextMock), "PSIQUIATRIA deve ser válido."),
                () -> assertTrue(validator.isValid("REUMATOLOGIA", contextMock), "REUMATOLOGIA deve ser válido."),
                () -> assertTrue(validator.isValid("UROLOGIA", contextMock), "UROLOGIA deve ser válido.")
        );
    }

    @Test
    void deveRetornarFalseAoReceberUmaEspecializacaoValidaEmLowerCase() {
        assertAll("Especializações em minúsculas.",
                () -> assertFalse(validator.isValid("imunologia", contextMock), "imunologia deve ser inválido."),
                () -> assertFalse(validator.isValid("anestesiologia", contextMock), "anestesiologia deve ser inválido."),
                () -> assertFalse(validator.isValid("angiologia", contextMock), "angiologia deve ser inválido."),
                () -> assertFalse(validator.isValid("cardiologia", contextMock), "cardiologia deve ser inválido."),
                () -> assertFalse(validator.isValid("clinica", contextMock), "clinica deve ser inválido."),
                () -> assertFalse(validator.isValid("coloproctologia", contextMock), "coloproctologia deve ser inválido."),
                () -> assertFalse(validator.isValid("dermatologia", contextMock), "dermatologia deve ser inválido."),
                () -> assertFalse(validator.isValid("endocrinologia", contextMock), "endocrinologia deve ser inválido."),
                () -> assertFalse(validator.isValid("gastroenterologia", contextMock), "gastroenterologia deve ser inválido."),
                () -> assertFalse(validator.isValid("genetica_medica", contextMock), "genetica_medica deve ser inválido."),
                () -> assertFalse(validator.isValid("geriatria", contextMock), "geriatria deve ser inválido."),
                () -> assertFalse(validator.isValid("ginecologia", contextMock), "ginecologia deve ser inválido."),
                () -> assertFalse(validator.isValid("hematologia", contextMock), "hematologia deve ser inválido."),
                () -> assertFalse(validator.isValid("infectologia", contextMock), "infectologia deve ser inválido."),
                () -> assertFalse(validator.isValid("mastologia", contextMock), "mastologia deve ser inválido."),
                () -> assertFalse(validator.isValid("esportiva", contextMock), "esportiva deve ser inválido."),
                () -> assertFalse(validator.isValid("pericia", contextMock), "pericia deve ser inválido."),
                () -> assertFalse(validator.isValid("nefrologia", contextMock), "nefrologia deve ser inválido."),
                () -> assertFalse(validator.isValid("neurologia", contextMock), "neurologia deve ser inválido."),
                () -> assertFalse(validator.isValid("oftalmologia", contextMock), "oftalmologia deve ser inválido."),
                () -> assertFalse(validator.isValid("oncologia", contextMock), "oncologia deve ser inválido."),
                () -> assertFalse(validator.isValid("ortopedia", contextMock), "ortopedia deve ser inválido."),
                () -> assertFalse(validator.isValid("otorrinolaringologia", contextMock), "otorrinolaringologia deve ser inválido."),
                () -> assertFalse(validator.isValid("patologia", contextMock), "patologia deve ser inválido."),
                () -> assertFalse(validator.isValid("pediatria", contextMock), "pediatria deve ser inválido."),
                () -> assertFalse(validator.isValid("pneumologia", contextMock), "pneumologia deve ser inválido."),
                () -> assertFalse(validator.isValid("psiquiatria", contextMock), "psiquiatria deve ser inválido."),
                () -> assertFalse(validator.isValid("reumatologia", contextMock), "reumatologia deve ser inválido."),
                () -> assertFalse(validator.isValid("urologia", contextMock), "urologia deve ser inválido.")
        );
    }

    @Test
    void deveRetornarFalseAoReceberUmaEspecializacaoInvalida() {
        assertAll("Especializações inválidas.",
                () -> assertFalse(validator.isValid("INVALIDO", contextMock), "INVALIDO deve ser inválido."),
                () -> assertFalse(validator.isValid("ANESTESIOLOGIAA", contextMock), "ANESTESIOLOGIAA deve ser inválido."),
                () -> assertFalse(validator.isValid("Cardiologia", contextMock), "Cardiologia deve ser inválido."),
                () -> assertFalse(validator.isValid("GINECOLOGIAA", contextMock), "GINECOLOGIAA deve ser inválido."),
                () -> assertFalse(validator.isValid("ortopedia", contextMock), "ortopedia deve ser inválido."),
                () -> assertFalse(validator.isValid("PEDIATRA", contextMock), "PEDIATRA deve ser inválido."),
                () -> assertFalse(validator.isValid("REUMATOLOGIAA", contextMock), "REUMATOLOGIAA deve ser inválido.")
        );
    }

    @Test
    void deveRetornarTrueAoReceberNuloOuVazio() {
        assertAll("Especializações nulas e vazias.",
                () -> assertTrue(validator.isValid(null, contextMock), "Nulo deve ser válido."),
                () -> assertTrue(validator.isValid("", contextMock), "Vazio deve ser válido."),
                () -> assertTrue(validator.isValid("   ", contextMock), "Espaço em branco deve ser válido.")
        );
    }
}