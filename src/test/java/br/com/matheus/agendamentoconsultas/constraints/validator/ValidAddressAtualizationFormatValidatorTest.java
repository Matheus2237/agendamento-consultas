package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ValidAddressAtualizationFormatValidatorTest {

	@Autowired
	private ValidAddressAtualizationFormatValidator validAddressAtualizationFormatValidator;
	
	@Test
	void deveRetornarTrueCasoForPassadoUmEnderecoComOFormatoValido() {
		String enderecoValido = "Av. Bandeirantes, 21, andar 4, apartamento 42, Jardim Planalto, São Paulo, SP, 32143-654";
		Boolean result = validAddressAtualizationFormatValidator.isValid(enderecoValido, null);
		assertTrue(result);
	}
	
	@Test
	void deveRetornarTrueCasoNaoForPassadoNenhumEndereco() {
		String enderecoNulo = null;
		Boolean result = validAddressAtualizationFormatValidator.isValid(enderecoNulo, null);
		assertTrue(result);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmEnderecoVazio() {
		String enderecoVazio = "";
		Boolean result = validAddressAtualizationFormatValidator.isValid(enderecoVazio, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseCasoForPassadoUmEnderecoMalEscrito() {
		String enderecoMalEscrito = "Av. Bandeirantes, não tem número, andar aleatório, apartamento abc, São Paulo, MP";
		Boolean result = validAddressAtualizationFormatValidator.isValid(enderecoMalEscrito, null);
		assertFalse(result);
	}
}
