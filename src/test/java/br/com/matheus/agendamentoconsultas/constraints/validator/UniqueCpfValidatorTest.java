package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;

@SpringBootTest
@ActiveProfiles("test")
class UniqueCpfValidatorTest {
	
	@Autowired
	private UniqueCpfValidator uniqueCpfValidator;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@BeforeEach
	public void setUp() {
		String cpf = "12345678900";
		Paciente paciente = new Paciente(
				"João Augusto",
				"jotaaug@gmail.com",
				"(54) 954332345",
				cpf,
				"Av. Bandeirantes, 21, andar 4, apartamento 42, Jardim Planalto, São Paulo, SP, 32143-654");
		pacienteRepository.save(paciente);
	}
	
	@Test
	public void deveRetornarVerdadeiroAoPassarUmCpfÚnicoNoSistema() {
		String cpf = "12345678901";
		Boolean result = uniqueCpfValidator.isValid(cpf, null);
		assertEquals(true, result);
	}
	
	@Test
	public void deveRetornarFalsoAoPassarUmCpfPreExistenteNoSistema() {
		String cpf = "12345678900";
		Boolean result = uniqueCpfValidator.isValid(cpf, null);
		assertEquals(false, result);
	}
}
