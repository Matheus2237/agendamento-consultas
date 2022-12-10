package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;

@SpringBootTest
@ActiveProfiles("test")
class UniqueEmailValidatorTest {

	@Autowired
	private UniqueEmailValidator uniqueEmailValidator;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@BeforeEach
	void preparandoMedico() {
		String emailMedico = "mantonieta@gmail.com";
		Medico medico = new Medico();
		medico.setEmail(emailMedico);
		medicoRepository.save(medico);
	}
	
	@BeforeEach
	void preparandoPaciente() {
		String emailPaciente = "jotaaug@gmail.com";
		Paciente paciente = new Paciente();
		paciente.setEmail(emailPaciente);
		pacienteRepository.save(paciente);
	}

	@Test
	void deveRetornarVerdadeiroAoPassarNoSistemaUmEmailDeMedicoUnico() {
		String email = "terezacramos@outlook.com";
		Boolean result = uniqueEmailValidator.isValid(email, null);
		assertEquals(true, result);
	}
	
	@Test
	void deveRetornarFalsoAoPassarUmEmailDeMedicoPreExistenteNoSistema() {
		String email = "mantonieta@gmail.com";
		Boolean result = uniqueEmailValidator.isValid(email, null);
		assertEquals(false, result);
	}
	
	@Test
	void deveRetornarVerdadeiroAoPassarNoSistemaUmEmailDePacienteUnico() {
		String email = "lucioalazevedo@yahoo.com.br";
		Boolean result = uniqueEmailValidator.isValid(email, null);
		assertEquals(true, result);
	}

	// Teste está falhando.
	@Test
	void deveRetornarFalsoAoPassarUmEmailDePacientePreExistenteNoSistema() {
		String email = "jotaaug@gmail.com";
		Boolean result = uniqueEmailValidator.isValid(email, null);
		assertEquals(false, result);
	}
}
