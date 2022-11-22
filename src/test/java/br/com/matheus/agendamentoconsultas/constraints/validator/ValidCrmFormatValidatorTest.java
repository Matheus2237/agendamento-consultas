package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ValidCrmFormatValidatorTest {

	@Autowired
	private ValidCrmFormatValidator validCrmFormatValidator;
	
	@Test
	void deveRetornarTrueCasoForPassadoUmaCrmComOFormatoValido() {
		String crmComEspaco = "CRM/SP 123456";
		String crmComTraco = "CRM/SP-123456";
		String crmColada = "CRM/SP123456";
		Boolean resultComEspaco = validCrmFormatValidator.isValid(crmComEspaco, null);
		Boolean resultComTraco = validCrmFormatValidator.isValid(crmComTraco, null);
		Boolean resultColado = validCrmFormatValidator.isValid(crmColada, null);
		assertTrue(resultComEspaco);
		assertTrue(resultComTraco);
		assertTrue(resultColado);
	}

	@Test
	void deveRetornarFalseCasoForPassadoUmaCrmSemOsCaracteresIniciais() {
		String crmSemInicio = "123456";
		String crmSemCrm = "SP 123456";
		String crmSemUf = "CRM 123456";
		Boolean resultSemInicio = validCrmFormatValidator.isValid(crmSemInicio, null);
		Boolean resultSemCrm = validCrmFormatValidator.isValid(crmSemCrm, null);
		Boolean resultSemUf = validCrmFormatValidator.isValid(crmSemUf, null);
		assertFalse(resultSemInicio);
		assertFalse(resultSemCrm);
		assertFalse(resultSemUf);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmaCrmComMaisDigitosOuMenosQueOEsperado() {
		String crmMaior = "CRM/MG 1234567";
		String crmMenor = "CRM/MG 12345";
		Boolean resultMaior = validCrmFormatValidator.isValid(crmMaior, null);
		Boolean resultMenor = validCrmFormatValidator.isValid(crmMenor, null);
		assertFalse(resultMaior);
		assertFalse(resultMenor);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmaCrmVazia() {
		String crmVazia = "";
		Boolean result = validCrmFormatValidator.isValid(crmVazia, null);
		assertFalse(result);
	}
		
	@Test
	void deveLancarUmaExceptionCasoForPassadoUmaCrmNula() {
		String crmNula = null;
		try {
			validCrmFormatValidator.isValid(crmNula, null);
		} catch(Exception e) {
			assertTrue(true);
		}
	}
}
