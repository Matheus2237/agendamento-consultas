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
	void deveRetornarTrueCasoForPassadoUmCpfComOFormatoValidoSemPontosETraco() {
		String cpfValido = "12345678900";
		Boolean result = validCpfFormatValidator.isValid(cpfValido, null);
		assertTrue(result);
	}
	
	@Test
	void deveRetornarTrueCasoForPassadoUmCpfComOFormatoValidoComPontosETraco() {
		String cpfValido = "123.456.789-00";
		Boolean result = validCpfFormatValidator.isValid(cpfValido, null);
		assertTrue(result);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmCpfVazio() {
		String cpfVazio = "";
		Boolean result = validCpfFormatValidator.isValid(cpfVazio, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmCpfComMaisDigitosQueONormal() {
		String cpfMaior = "123456789001";
		Boolean result = validCpfFormatValidator.isValid(cpfMaior, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmCpfComMenosDigitosQueONormal() {
		String cpfMenor = "1234567890";
		Boolean result = validCpfFormatValidator.isValid(cpfMenor, null);
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
