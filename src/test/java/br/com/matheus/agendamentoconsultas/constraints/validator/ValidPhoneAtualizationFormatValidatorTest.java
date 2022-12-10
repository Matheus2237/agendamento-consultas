package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ValidPhoneAtualizationFormatValidatorTest {

	@Autowired
	private ValidPhoneAtualizationFormatValidator validPhoneAtualizationFormatValidator;
	
	@Test
	void deveRetornarTrueAoPassarUmTelefoneVálido() {
		String telefoneMovelDDDParenteseEEspaco = "(35) 912345678";
		String telefoneMovelDDDParenteseSemEspaco = "(35)912345678";
		String telefoneMovelDDDSemParenteseEComEspaco = "35 912345678";
		String telefoneMovelDDDSemParenteseESemEspaco = "35912345678";
		String telefoneFixoDDDParenteseEEspaco = "(35) 35511234"; 
		String telefoneFixoDDDParenteseSemEspaco = "(35)35511234";
		String telefoneFixoDDDSemParenteseEComEspaco = "35 35511234";
		String telefoneFixoDDDSemParenteseESemEspaco = "3535511234";
		Boolean resultMovelDDDParenteseEEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneMovelDDDParenteseEEspaco, null);
		Boolean resultMovelDDDParenteseSemEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneMovelDDDParenteseSemEspaco, null);
		Boolean resultMovelDDDSemParenteseEComEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneMovelDDDSemParenteseEComEspaco, null);
		Boolean resultMovelDDDSemParenteseESemEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneMovelDDDSemParenteseESemEspaco, null);
		Boolean resultFixoDDDParenteseEEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneFixoDDDParenteseEEspaco, null);
		Boolean resultFixoDDDParenteseSemEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneFixoDDDParenteseSemEspaco, null);
		Boolean resultFixoDDDSemParenteseEComEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneFixoDDDSemParenteseEComEspaco, null);
		Boolean resultFixoDDDSemParenteseESemEspaco = validPhoneAtualizationFormatValidator.isValid(telefoneFixoDDDSemParenteseESemEspaco, null);
		assertTrue(resultMovelDDDParenteseEEspaco);
		assertTrue(resultMovelDDDParenteseSemEspaco);
		assertTrue(resultMovelDDDSemParenteseEComEspaco);
		assertTrue(resultMovelDDDSemParenteseESemEspaco);
		assertTrue(resultFixoDDDParenteseEEspaco);
		assertTrue(resultFixoDDDParenteseSemEspaco);
		assertTrue(resultFixoDDDSemParenteseEComEspaco);
		assertTrue(resultFixoDDDSemParenteseESemEspaco);
	}
	
	@Test
	void deveRetornarTrueAoPassarUmTelefoneNulo() {
		String telefoneNulo = null;
		Boolean result = validPhoneAtualizationFormatValidator.isValid(telefoneNulo, null);
		assertTrue(result);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmTelefoneVazio() {
		String telefoneVazio = "";
		Boolean result = validPhoneAtualizationFormatValidator.isValid(telefoneVazio, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmTelefoneComMaisDigitosOuMenosQueOEsperado() {
		String telefoneMaior = "(35) 91234567890";
		String telefoneMenor = "(35) 9123456";
		Boolean resultMaior = validPhoneAtualizationFormatValidator.isValid(telefoneMaior, null);
		Boolean resultMenor = validPhoneAtualizationFormatValidator.isValid(telefoneMenor, null);
		assertFalse(resultMaior);
		assertFalse(resultMenor);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmTelefoneComQuantidadeIncorretaDeDigitosDoDDD() {
		String telefoneDDDMaior = "(355) 912345678";
		String telefoneDDDMenor = "(3) 912345678";  
		Boolean resultDDDMaior = validPhoneAtualizationFormatValidator.isValid(telefoneDDDMaior, null);
		Boolean resultDDDMenor = validPhoneAtualizationFormatValidator.isValid(telefoneDDDMenor, null);
		assertFalse(resultDDDMaior);
		assertFalse(resultDDDMenor);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmNumeroTelefonicoMisturadoComLetras() {
		String telefoneComLetras = "(35) 9987554r3";
		Boolean resultComLetras = validPhoneAtualizationFormatValidator.isValid(telefoneComLetras, null);
		assertFalse(resultComLetras);
	}
}
