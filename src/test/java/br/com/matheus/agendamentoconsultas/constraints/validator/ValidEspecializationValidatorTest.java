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
class ValidEspecializationValidatorTest {
	
	@Autowired
	private ValidEspecializationValidator validEspecializationValidator;
	
	@Test
	void deveRetornarVerdadeiroAoPassarUmaEspecializacaoExistenteNoSistemaComAGrafiaCorreta() {
		String especializacaoNome = "Pediatria";
		Boolean result = validEspecializationValidator.isValid(especializacaoNome, null);
		assertTrue(result);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmaEspecializacaoExistenteNoSistemaComAGrafiaIncorreta() {
		String especializacaoNomeErrado = "Pedaitraia";
		Boolean result = validEspecializationValidator.isValid(especializacaoNomeErrado, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmaEspecializacaoNaoExistenteNoSistema() {
		String especializacaoNaoExistente = "Veterinaria";
		Boolean result = validEspecializationValidator.isValid(especializacaoNaoExistente, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarFalseAoPassarUmaEspecializacaoVazia() {
		String especializacaoVazia = "";
		Boolean result = validEspecializationValidator.isValid(especializacaoVazia, null);
		assertFalse(result);
	}
	
	@Test
	void deveRetornarUmaExceptionAoPassarUmaEspecializacaoNula() {
		String especializacaoNula = null;
		assertThrows(NullPointerException.class, () -> validEspecializationValidator.isValid(especializacaoNula, null));
	}
}
