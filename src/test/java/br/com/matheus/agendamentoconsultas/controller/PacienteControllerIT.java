package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.base.DatabaseProvidedIntegrationTest;
import br.com.matheus.agendamentoconsultas.base.json.HttpBodyJsonSource;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PacienteControllerIT extends DatabaseProvidedIntegrationTest {

    private static final String PACIENTE_INSERT_SCRIPT = "classpath:sql/paciente/insert.sql";

    private final MockMvc mockMvc;

    @Autowired
    PacienteControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/cadastro_sucesso.json")
    @SneakyThrows
    void deveRetornar201AoCadastrarUmPacienteComSucesso(String request, String expectedResponse){
        final String createdURI = "/paciente/1";
        mockMvc.perform(
                post("/paciente")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(createdURI)))
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/cadastro_informacoes_faltantes.json")
    @SneakyThrows
    void deveRetornar400AoTentarCadastrarUmPacienteComAlgumDadoFaltanteOuEmBranco(String request, String expectedResponse) {
        mockMvc.perform(
                post("/paciente")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/cadastro_cpf_email_repetidos.json")
    @Sql(scripts = PACIENTE_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar400AoTentarCadastrarUmPacienteComCPFOuEmailJaExistentesNoBancoDeDados(String request, String expectedResponse) {
        mockMvc.perform(
                post("/paciente")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }



















//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoGetParaRetornarTodosOsPacientesCadastrados() throws Exception {
//		URI uri = new URI("/paciente");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoGetParaUmaURINaoExistente() throws Exception {
//		URI uri = new URI("uriquenaoexiste");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoGetParaRetornarUmPacienteEspeficoAPartirDoSeuId() throws Exception {
//		URI uri = new URI("/paciente/1");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoGetParaRetornarUmPacienteEspeficoAPartirDeUmIdNaoExistente() throws Exception {
//		URI uri = new URI("/paciente/100");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo201AoEnviarUmaRequisicaoPostParaCadastrarUmPacienteNoBancoDeDadosPassandoOsDadosCorretamente() throws Exception {
//		URI uri = new URI("/paciente");
//		String json = "{\r\n"
//					+ "    \"nome\":\"João Augusto\",\r\n"
//					+ "    \"email\":\"jotaaug@gmail.com\",\r\n"
//					+ "    \"telefone\":\"(54) 954332345\",\r\n"
//					+ "    \"cpf\":\"14234254253\",\r\n"
//					+ "    \"endereco\":\"Av. Bandeirantes, 21, andar 4, apartamento 42, Jardim Planalto, São Paulo, SP, 32143-654\"\r\n"
//					+ "}";
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.post(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(201));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo400AoEnviarUmaRequisicaoPostParaCadastrarUmPacienteNoBancoDeDadosPassandoDadosInvalidos() throws Exception {
//		URI uri = new URI("/paciente");
//		String json = "{\r\n"
//					+ "    \"nome\":\"Milena Alves\",\r\n"
//					+ "    \"email\":\"miihil.com\",\r\n"
//					+ "    \"telefone\":\"(23) 912321\",\r\n"
//					+ "    \"cpf\":\"1433\",\r\n"
//					+ "    \"endereco\":\"Av. Bandeirantes, 21, andar 5, apartamento 53, lo, SP, 32143-654\"\r\n"
//					+ "}";
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.post(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(400));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoPutParaAtualizarOsDadosDeUmPacienteQueExisteNoBancoDedDadosPassandoDadosValidos() throws Exception {
//		URI uri = new URI("/paciente/1");
//		String json = "{\r\n"
//					+ "    \"telefone\":\"(23) 94522-4321\",\r\n"
//					+ "    \"endereco\":\"R. Alfeu Seron Júnior, 270, bl4, apto 201, Jardim Venetto II, Sertãozinho, SP, 14169622\"\r\n"
//					+ "}";
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.put(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo400AoEnviarUmaRequisicaoPutParaAtualizarOsDadosDeUmPacienteQueExisteNoBancoDedDadosPassandoDadosInvalidos() throws Exception {
//		URI uri = new URI("/paciente/1");
//		String json = "{\r\n"
//					+ "    \"telefone\":\"(23) 9451\",\r\n"
//					+ "    \"endereco\":\"R. Alfeu Seron Júnior, 270, bl4, apto 201, Jardim Venetto II, Sertãozinho, SP, 14169622\"\r\n"
//					+ "}";
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.put(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(400));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoPutParaAtualizarOsDadosDeUmPacienteQueExisteNoBancoDedDados() throws Exception {
//		URI uri = new URI("/paciente/100");
//		String json = "{\r\n"
//					+ "    \"telefone\":\"(23) 94522-4321\",\r\n"
//					+ "    \"endereco\":\"R. Alfeu Seron Júnior, 270, bl4, apto 201, Jardim Venetto II, Sertãozinho, SP, 14169622\"\r\n"
//					+ "}";
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.put(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoDeleteParaExcluirUmPacienteQueExisteNoBandoDeDados() throws Exception {
//		URI uri = new URI("/paciente/1");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.delete(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoDeleteParaExcluirUmPacienteQueNaoExisteNoBandoDeDados() throws Exception {
//		URI uri = new URI("/paciente/100");
//		mockMvc.perform(MockMvcRequestBuilders
//				.delete(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
}
