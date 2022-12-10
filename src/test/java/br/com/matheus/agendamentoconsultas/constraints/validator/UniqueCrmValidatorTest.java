package br.com.matheus.agendamentoconsultas.constraints.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

@SpringBootTest
@ActiveProfiles("test")
class UniqueCrmValidatorTest {

	@Autowired
	private UniqueCrmValidator uniqueCrmValidator;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@BeforeEach
	void preparandoMedico() {
		String crm = "CRM/SP 123456"; 
		Medico medico = new Medico();
		medico.setCrm(crm);
		medicoRepository.save(medico);
	}

	@Test
	void deveRetornarVerdadeiroAoPassarUmaCRMUnicaNoSistema() {
		String crm = "CRM/MG 654321";
		Boolean result = uniqueCrmValidator.isValid(crm, null);
		assertEquals(true, result);
	}
	
	@Test
	void deveRetornarFalsoAoPassarUmaCRMPreExistenteNoSistema() {
		String crm = "CRM/SP 123456"; 
		Boolean result = uniqueCrmValidator.isValid(crm, null);
		assertEquals(false, result);
	}
}
