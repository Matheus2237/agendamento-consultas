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
class ValidAddressFormatValidatorTest {
	
	@Autowired
	private ValidEnderecoDTOValidator validAddressFormatValidator;
	
	@Test
	void deveRetornarTrueCasoForPassadoUmEnderecoComOFormatoValido() {
		String enderecoValido = "R. Amadeu Garcia, 33, Jardim Rosário, Guapiraba, MG, 37806-875";
		Boolean result = validAddressFormatValidator.isValid(enderecoValido, null);
		assertTrue(result);
	}
	
	@Test
	void deveEsperarUmaExceptionCasoNaoForPassadoNenhumEndereco() {
		String enderecoNulo = null;
		assertThrows(NullPointerException.class, () -> validAddressFormatValidator.isValid(enderecoNulo, null));		
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmEnderecoVazio() {
		String enderecoVazio = "";
		Boolean result = validAddressFormatValidator.isValid(enderecoVazio, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmEnderecoMalEscrito() {
		String enderecoMalEscrito = "ra Amadeu, um número, Jardim Rosário, Guarapira, MRSA, 3434343434343";
		Boolean result = validAddressFormatValidator.isValid(enderecoMalEscrito, null);
		assertFalse(result);
	}
}
