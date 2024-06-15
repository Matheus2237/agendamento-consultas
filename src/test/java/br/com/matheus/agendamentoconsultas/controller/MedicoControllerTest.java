package br.com.matheus.agendamentoconsultas.controller;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
class MedicoControllerTest {

//	@Autowired
//	private MedicoRepository medicoRepository;
//
//	@Autowired
//	private MockMvc mockMvc;
	
//	@BeforeEach
//	private void popularBancoDeDacosComUmRegistroDeUmMedico() {
//		String nome = "Matheus Paulino Ribeiro";
//		String email = "emaildeteste@email.com";
//		String telefone = "16988776655";
//		Especializacao especializacao = Especializacao.CARDIOLOGIA;
//		String crm = "CRM/SP 123456";
//		String endereco = "Rua Professor Matheus Paulino, 360, bl4, apto101, Jardim das Orquídeas, Sertãozinho, SP, 14169-777";
//		Medico medico = new Medico(nome, email, telefone, especializacao, crm, endereco);
//		medicoRepository.save(medico);
//	}
	
//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoGetParaRetornarTodosOsMedicosCadastrados() throws Exception {
//		URI uri = new URI("/medico");
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
//		URI uri = new URI("/uriquenaoexiste");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoGetParaRetornarUmMedicoEspeficoAPartirDoSeuId() throws Exception {
//		URI uri = new URI("/medico/1");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoGetParaRetornarUmMedicoEspeficoAPartirDeUmIdInexistente() throws Exception {
//		URI uri = new URI("/medico/100");
//		mockMvc
//		.perform(MockMvcRequestBuilders
//				.get(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo201AoEnviarUmaRequisicaoPostParaCadastrarUmMedicoNoBancoDeDadosPassandoOsDadosCorretamente() throws Exception {
//		URI uri = new URI("/medico");
//		String json = "{\r\n"
//					+ "    \"nome\":\"Maria Antonieta Goulart\",\r\n"
//					+ "    \"email\":\"mantonieta@gmail.com\",\r\n"
//					+ "    \"telefone\":\"16933654120\",\r\n"
//					+ "    \"especializacao\":\"Pediatria\",\r\n"
//					+ "    \"crm\":\"CRM/SP 459870\",\r\n"
//					+ "    \"endereco\":\"Rua Alfeu Seron Júnior, 270, bl4, apto101, Jardim Venetto II, Sertãozinho, SP, 14169622\"\r\n"
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
//	public void deveriaDevolverCodigo400AoEnviarUmaRequisicaoPostParaCadastrarUmMedicoNoBancoDeDadosPassandoDadosInvalidos() throws Exception {
//		URI uri = new URI("/medico");
//		String json = "{\r\n"
//					+ "    \"nome\":\"Maria Antonieta Goulart\",\r\n"
//					+ "    \"email\":\"mantonie\",\r\n"
//					+ "    \"telefone\":\"1620\",\r\n"
//					+ "    \"especializacao\":\"Ortpdia\",\r\n"
//					+ "    \"crm\":\"CRM459870\",\r\n"
//					+ "    \"endereco\":\"Rua Atto II, Sertãozinho, SP, 14169622\"\r\n"
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
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoPutParaAtualizarOsDadosDeUmMedicoQueExisteNoBancoDedDadosPassandoDadosValidos() throws Exception {
//		URI uri = new URI("/medico/1");
//		String json = "{\r\n"
//					+ "    \"telefone\":\"35988086808\",\r\n"
//					+ "    \"especializacao\":\"Cardiologia\"\r\n"
//					+ "}";
//		mockMvc.perform(MockMvcRequestBuilders
//				.put(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo400AoEnviarUmaRequisicaoPutParaAtualizarOsDadosDeUmMedicoQueExisteNoBancoDedDadosPassandoDadosInvalidos() throws Exception {
//		URI uri = new URI("/medico/1");
//		String json = "{\r\n"
//					+ "    \"telefone\":\"(35) 988\",\r\n"
//					+ "    \"especializacao\":\"pediattia\"\r\n"
//					+ "}";
//		mockMvc.perform(MockMvcRequestBuilders
//				.put(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(400));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoPutParaAtualizarOsDadosDeUmMedicoQueExisteNoBancoDedDados() throws Exception {
//		URI uri = new URI("/medico/100");
//		String json = "{\r\n"
//					+ "    \"telefone\":\"35988086808\",\r\n"
//					+ "    \"especializacao\":\"Cardiologia\"\r\n"
//					+ "}";
//		mockMvc.perform(MockMvcRequestBuilders
//				.put(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo200AoEnviarUmaRequisicaoDeleteParaExcluirUmMedicoQueExisteNoBandoDeDados() throws Exception {
//		URI uri = new URI("/medico/2");
//		mockMvc.perform(MockMvcRequestBuilders
//				.delete(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(200));
//	}
//
//	@Test
//	public void deveriaDevolverCodigo404AoEnviarUmaRequisicaoDeleteParaExcluirUmMedicoQueNaoExisteNoBandoDeDados() throws Exception {
//		URI uri = new URI("/medico/100");
//		mockMvc.perform(MockMvcRequestBuilders
//				.delete(uri))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(404));
//	}
}

