package br.com.matheus.agendamentoconsultas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.matheus.agendamentoconsultas.model.Paciente;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PacienteRepositoryTest {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void deveRetornarVerdadeiroSeExistirNoBancoDeDadosUmRegistroDeUmPacienteBuscandoPorUmEmail() {
		popularBancoDedDadosComUmPaciente();
		String email = "emaildeteste@email.com";
		Boolean resultadoDaBusca = pacienteRepository.existsByEmail(email);
		assertEquals(true, resultadoDaBusca);
	}
	
	@Test
	public void deveRetornarFalsoSeNaoExistirNoBancoDeDadosUmRegistroDeUmPacienteBuscandoPorUmEmail() {
		popularBancoDedDadosComUmPaciente();
		String emailErrado = "emailerrado@email.com";
		Boolean resultadoDaBusca = pacienteRepository.existsByEmail(emailErrado);
		assertEquals(false, resultadoDaBusca);
	}
	
	@Test
	public void deveRetornarVerdadeiroSeExistirNoBancoDeDadosUmRegistroDeUmPacienteBuscandoPorUmCPF() {
		popularBancoDedDadosComUmPaciente();
		String cpf = "12345678910";
		Boolean resultadoDaBusca = pacienteRepository.existsByCpf(cpf);
		assertEquals(true, resultadoDaBusca);
	}
	
	@Test
	public void deveRetornarFalsoSeNaoExistirNoBancoDeDadosUmRegistroDeUmPacienteBuscandoPorUmCPF() {
		popularBancoDedDadosComUmPaciente();
		String cpfErrado = "10987654321";
		Boolean resultadoDaBusca = pacienteRepository.existsByCpf(cpfErrado);
		assertEquals(false, resultadoDaBusca);
	}
	
	private void popularBancoDedDadosComUmPaciente() {
		String nome = "Matheus Paulino Ribeiro";
		String email = "emaildeteste@email.com";
		String telefone = "16912345678";
		String cpf = "12345678910";
		String endereco = "Rua Professor Matheus Paulino, 360, bl4, apto101, Jardim das Orquídeas, Sertãozinho, SP, 14169-777";
		Paciente paciente = new Paciente(nome, email, telefone, cpf, endereco);
		testEntityManager.persist(paciente);
	}
}
