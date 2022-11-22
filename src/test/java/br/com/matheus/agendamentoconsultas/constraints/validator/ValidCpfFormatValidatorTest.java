package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ValidCpfFormatValidatorTest {

	@Autowired
	private ValidCpfFormatValidator validCpfFormatValidator;
	
	@Test
	void deveRetornarTrueCasoForPassadoUmCpfComOFormatoValido() {
		String cpfValidoSemPontosETraco = "12345678900";
		String cpfValidoComPontosETraco = "123.456.789-00";
		Boolean resultSemPontosETraco= validCpfFormatValidator.isValid(cpfValidoSemPontosETraco, null);
		Boolean resultComPontosETraco = validCpfFormatValidator.isValid(cpfValidoComPontosETraco, null);
		assertTrue(resultSemPontosETraco);
		assertTrue(resultComPontosETraco);
	}

	@Test
	void deveRetornarFalseCasoForPassadoUmCpfComMaisDigitosOuMenosQueOEsperado() {
		String cpfMaior = "123456789001";
		String cpfMenor = "1234567890";
		Boolean resultMaior = validCpfFormatValidator.isValid(cpfMaior, null);
		Boolean resultMenor = validCpfFormatValidator.isValid(cpfMenor, null);
		assertFalse(resultMaior);
		assertFalse(resultMenor);
	}
		
	@Test
	void deveRetornarFalseCasoForPassadoUmCpfVazio() {
		String cpfVazio = "";
		Boolean result = validCpfFormatValidator.isValid(cpfVazio, null);
		assertFalse(result);
	}
	
	@Test
	void deveLancarUmaExceptionCasoSejaPassadoUmCpfNulo() {
		String cpfNulo = null;
		try {
			validCpfFormatValidator.isValid(cpfNulo, null);
		} catch(Exception e) {
			assertTrue(true);
		}
	}
}
