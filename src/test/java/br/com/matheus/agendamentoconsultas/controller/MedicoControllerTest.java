package br.com.matheus.agendamentoconsultas.controller;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MedicoControllerTest {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	private void popularBancoDeDacosComUmRegistroDeUmMedico() {
		String nome = "Matheus Paulino Ribeiro";
		String email = "emaildeteste@email.com";
		String telefone = "16988776655";
		Especializacao especializacao = Especializacao.CARDIOLOGIA;
		String crm = "CRM/SP 123456";
		String endereco = "Rua Professor Matheus Paulino, 360, bl4, apto101, Jardim das Orquídeas, Sertãozinho, SP, 14169-777";
		Medico medico = new Medico(nome, email, telefone, especializacao, crm, endereco);
		medicoRepository.save(medico);
	}
	
	@Test
	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoGetParaRetornarTodosOsMedicosCadastrados() throws Exception {
		URI uri = new URI("/medico");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	@Test
	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoParaUmaURINaoExistente() throws Exception {
		URI uri = new URI("/uriquenaoexiste");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404));
	}
	
	@Test
	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoGetParaRetornarUmMedicoEspeficoAPartirDoSeuId() throws Exception {
		URI uri = new URI("/medico/1");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	@Test
	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoGetParaRetornarUmMedicoEspeficoAPartirDoSeuId() throws Exception {
		URI uri = new URI("/medico/100");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404));
	}
	
	@Test
	public void deveriaDevolverCodigo201AoCadastrarUmMedicoNoBancoDeDadosPassandoOsDadosCorretamente() throws Exception {
		URI uri = new URI("/medico");
		String json = ""
				+ "{\r\n"
				+ "    \"nome\":\"Maria Antonieta Goulart\",\r\n"
				+ "    \"email\":\"mantonieta@gmail.com\",\r\n"
				+ "    \"telefone\":\"16933654120\",\r\n"
				+ "    \"especializacao\":\"Pediatria\",\r\n"
				+ "    \"crm\":\"CRM/SP 459870\",\r\n"
				+ "    \"endereco\":\"Rua Alfeu Seron Júnior, 270, bl4, apto101, Jardim Venetto II, Sertãozinho, SP, 14169622\"\r\n"
				+ "}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
	}
	
	@Test
	public void deveriaDevolverCodigo400AoCadastrarUmMedicoNoBancoDeDadosPassandoDadosInvalidos() throws Exception {
		URI uri = new URI("/medico");
		String json = "{\r\n"
				+ "    \"nome\":\"Maria Antonieta Goulart\",\r\n"
				+ "    \"email\":\"mantonie\",\r\n"
				+ "    \"telefone\":\"1620\",\r\n"
				+ "    \"especializacao\":\"Ortpdia\",\r\n"
				+ "    \"crm\":\"CRM459870\",\r\n"
				+ "    \"endereco\":\"Rua Atto II, Sertãozinho, SP, 14169622\"\r\n"
				+ "}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
}
