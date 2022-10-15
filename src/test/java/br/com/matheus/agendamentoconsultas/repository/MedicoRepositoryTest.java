package br.com.matheus.agendamentoconsultas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;

	@BeforeEach
	private void popularBancoDeDacosComUmRegistroDeUmMedico() {
		String nome = "Matheus Paulino Ribeiro";
		String email = "emaildeteste@email.com";
		String telefone = "16988776655";
		Especializacao especializacao = Especializacao.CARDIOLOGIA;
		String crm = "CRM/SP 123456";
		String endereco = "Rua Professor Matheus Paulino, 360, bl4, apto101, Jardim das Orquídeas, Sertãozinho, SP, 14169-777";
		Medico medico = new Medico(nome, email, telefone, especializacao, crm, endereco);
		testEntityManager.persist(medico);
	}
	
	@Test
	public void deveRetornarVerdadeiroSeExistirNoBancoDeDadosUmRegistroDeUmMedicoBuscandoPorUmEmail() {
		String email = ("emaildeteste@email.com");
		Boolean resultadoDaBusca = medicoRepository.existsByEmail(email);
		assertEquals(true, resultadoDaBusca);
	}
	
	@Test
	public void deveRetornarFalsoSeNaoExistirNoBancoDeDadosUmRegistroDeUmMedicoBuscandoPorUmEmail() {
		String email = ("outroemaildeteste@email.com");
		Boolean resultadoDaBusca = medicoRepository.existsByEmail(email);
		assertEquals(false, resultadoDaBusca);
	}
	
	@Test
	public void deveRetornarVerdadeiroSeExistirNoBancoDeDadosUmRegistroDeUmMedicoBuscandoPorUmaCRM() {
		String crm = "CRM/SP 123456";
		Boolean resultadoDaBusca = medicoRepository.existsByCrm(crm);
		assertEquals(true, resultadoDaBusca);
	}
	
	@Test
	public void deveRetornarFalsoSeNaoExistirNoBancoDeDadosUmRegistroDeUmMedicoBuscandoPorUmaCRM() {
		String crm = "CRM/SP 654321";
		Boolean resultadoDaBusca = medicoRepository.existsByCrm(crm);
		assertEquals(false, resultadoDaBusca);
	}
}
