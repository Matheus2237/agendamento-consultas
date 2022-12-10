package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ValidPhoneFormatValidatorTest {

	@Autowired
	private ValidPhoneFormatValidator validPhoneFormatValidator;
	
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
		Boolean resultMovelDDDParenteseEEspaco = validPhoneFormatValidator.isValid(telefoneMovelDDDParenteseEEspaco, null);
		Boolean resultMovelDDDParenteseSemEspaco = validPhoneFormatValidator.isValid(telefoneMovelDDDParenteseSemEspaco, null);
		Boolean resultMovelDDDSemParenteseEComEspaco = validPhoneFormatValidator.isValid(telefoneMovelDDDSemParenteseEComEspaco, null);
		Boolean resultMovelDDDSemParenteseESemEspaco = validPhoneFormatValidator.isValid(telefoneMovelDDDSemParenteseESemEspaco, null);
		Boolean resultFixoDDDParenteseEEspaco = validPhoneFormatValidator.isValid(telefoneFixoDDDParenteseEEspaco, null);
		Boolean resultFixoDDDParenteseSemEspaco = validPhoneFormatValidator.isValid(telefoneFixoDDDParenteseSemEspaco, null);
		Boolean resultFixoDDDSemParenteseEComEspaco = validPhoneFormatValidator.isValid(telefoneFixoDDDSemParenteseEComEspaco, null);
		Boolean resultFixoDDDSemParenteseESemEspaco = validPhoneFormatValidator.isValid(telefoneFixoDDDSemParenteseESemEspaco, null);
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
	void deveSerLancadaUmaExceptionAoPassarUmTelefoneNulo() {
		String telefoneNulo = null;
		assertThrows(NullPointerException.class, () -> validPhoneFormatValidator.isValid(telefoneNulo, null));
	}
	
	@Test
	void deveRetornarFalseAoPassarUmTelefoneVazio() {
		String telefoneVazio = "";
		Boolean result = validPhoneFormatValidator.isValid(telefoneVazio, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmTelefoneComMaisDigitosOuMenosQueOEsperado() {
		String telefoneMaior = "(35) 91234567890";
		String telefoneMenor = "(35) 9123456";
		Boolean resultMaior = validPhoneFormatValidator.isValid(telefoneMaior, null);
		Boolean resultMenor = validPhoneFormatValidator.isValid(telefoneMenor, null);
		assertFalse(resultMaior);
		assertFalse(resultMenor);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmTelefoneComQuantidadeIncorretaDeDigitosDoDDD() {
		String telefoneDDDMaior = "(355) 912345678";
		String telefoneDDDMenor = "(3) 912345678";  
		Boolean resultDDDMaior = validPhoneFormatValidator.isValid(telefoneDDDMaior, null);
		Boolean resultDDDMenor = validPhoneFormatValidator.isValid(telefoneDDDMenor, null);
		assertFalse(resultDDDMaior);
		assertFalse(resultDDDMenor);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmNumeroTelefonicoMisturadoComLetras() {
		String telefoneComLetras = "(35) 9987554r3";
		Boolean resultComLetras = validPhoneFormatValidator.isValid(telefoneComLetras, null);
		assertFalse(resultComLetras);
	}
}
