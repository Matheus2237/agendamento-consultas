package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class UniqueCrmValidatorTest extends MockedUnitTest {

    @InjectMocks
    private UniqueCrmValidator validator;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private ConstraintValidatorContext context;

    private static final String CRM = "SP123456";

    @Test
    void deveRetornarVerdadeiroAoPassarUmCrmUnicoNoSistema() {
        when(medicoRepository.existsByCrmValue(CRM)).thenReturn(false);
        boolean isValid = validator.isValid(CRM, context);
        assertTrue(isValid, "Deve retornar verdadeiro quando não existir esse crm no sistema.");
    }

    @Test
    void deveRetornarFalsoAoPassarUmCrmJaExistenteNoSistema() {
        when(medicoRepository.existsByCrmValue(CRM)).thenReturn(true);
        boolean isValid = validator.isValid(CRM, context);
        assertFalse(isValid, "Deve retornar falso ao existir um crm no sistema.");
    }
}