package br.com.matheus.agendamentoconsultas.controller;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
class PacienteControllerTest {

//	@Autowired
//	private PacienteRepository pacienteRepository;
//
//	@Autowired
//	private MockMvc mockMvc;
	
//	@BeforeEach
//	public void popularUmBancoDeDadosComORegistroDeUmPaciente() throws Exception {
//		String nome = "Matheus Paulino Ribeiro";
//		String email = "emaildeteste@email.com";
//		String telefone = "16988776655";
//		String cpf = "12345678910";
//		String endereco = "Rua Professor Matheus Paulino, 360, bl4, apto101, Jardim das Orquídeas, Sertãozinho, SP, 14169-777";
//		Paciente paciente = new Paciente(nome, email, telefone, cpf, endereco);
//		pacienteRepository.save(paciente);
//	}
	
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
