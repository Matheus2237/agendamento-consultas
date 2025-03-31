package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.controller.dto.*;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import br.com.matheus.agendamentoconsultas.repository.HorarioAtendimentoRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static br.com.matheus.agendamentoconsultas.base.AbstractValidacaoAgendamentoConsultaBaseTest.setPrivateField;
import static br.com.matheus.agendamentoconsultas.util.ReflectionUtil.setPrivateId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MedicoServiceTest extends MockedUnitTest {

    @InjectMocks
    private MedicoService medicoService;

    @Spy
    private MedicoRepository medicoRepositorySpy;

    @Spy
    private HorarioAtendimentoRepository horarioAtendimentoRepositorySpy;

    @Mock
    private Pageable pageableMock;

    private final Long id = 1L;
    private final String nome = "Nome";
    private final String crm = "MG000000";
    private final String email = "email@email.com";
    private final String ddd = "11";
    private final String numeroTelefone = "111111111";
    private final String logradouro = "Rua Logradouro";
    private final String numeroEndereco = "11";
    private final String bairro = "Bairro";
    private final String cidade = "Cidade";
    private final String uf = "MG";
    private final String cep = "11111111";
    private final String especializacao = "CARDIOLOGIA";
    private final String diaDaSemana = "SEGUNDA";
    private final String horaInicial = "08:00";
    private final String horaFinal = "16:00";

    @Test
    void deveRetornarUmaPaginaDeMedicosCadastrados() {
        List<Medico> medicos = Collections.singletonList(getMedico());
        Page<Medico> medicosPage = new PageImpl<>(medicos, pageableMock, medicos.size());
        when(medicoRepositorySpy.findAll(pageableMock)).thenReturn(medicosPage);
        Page<ResponseTodosMedicosDTO> responseMedicosPage = medicoService.visualizarTodos(pageableMock);
        assertAll("Retorno deve ser uma paginação válida.",
                () -> assertNotNull(responseMedicosPage, "Paginação não é nula."),
                () -> assertNotNull(responseMedicosPage.getContent().getFirst(), "O conteúdo da paginação não é nulo"),
                () -> assertEquals(1, responseMedicosPage.getTotalElements(), "O número total de elementos deve ser um."),
                () -> assertInstanceOf(ResponseTodosMedicosDTO.class, responseMedicosPage.getContent().getFirst(), "O elemento da paginação é uma instância de um ResponseTodosPacientes DTO.")
        );
    }

    @Test
    void deveCadastrarUmMedicoComDadosValidos() {
        when(medicoRepositorySpy.save(any(Medico.class))).thenAnswer(i -> i.getArgument(0));
        RequestCadastroMedicoDTO medicoDTO = getRequestCadastroMedicoDTO();
        Medico medicoCadastrado = medicoService.cadastrar(medicoDTO);
        assertNotNull(medicoCadastrado, "Medico cadastrado não deve ser nulo.");
        Set<HorarioAtendimento> horariosAtendimento = medicoCadastrado.getHorariosAtendimento();
        assertAll("Dados do paciente persistido devem ser consistentes com o que foi recebido.",
                () -> assertEquals(nome, medicoCadastrado.getNome(), "Nome deve ser o mesmo."),
                () -> assertEquals(crm, medicoCadastrado.getCrm().getValue(), "CRM deve ser o mesmo."),
                () -> assertEquals(email, medicoCadastrado.getEmail().getValue(), "Email deve ser o mesmo."),
                () -> assertEquals(ddd, medicoCadastrado.getTelefone().getDdd(), "DDD de telefone deve ser o mesmo."),
                () -> assertEquals(numeroTelefone, medicoCadastrado.getTelefone().getNumero(), "Número de telefone deve ser o mesmo."),
                () -> assertEquals(logradouro, medicoCadastrado.getEndereco().getLogradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEndereco, medicoCadastrado.getEndereco().getNumero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairro, medicoCadastrado.getEndereco().getBairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, medicoCadastrado.getEndereco().getCidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, medicoCadastrado.getEndereco().getUf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, medicoCadastrado.getEndereco().getCep(), "CEP do endereço deve ser o mesmo."),
                () -> assertEquals(especializacao, medicoCadastrado.getEspecializacao().toString(), "Especializacao deve ser a mesma."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> diaDaSemana.equals(h.getPrimaryKey().getDiaDaSemana().toString())), "Dia da semana do horário de atendimento deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaInicial.equals(h.getHoraInicial().toString())), "Hora inicial do horário de atendimento do endereço deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaFinal.equals(h.getHoraFinal().toString())), "Hora final do horário de atendimento do endereço deve ser o mesmo.")
        );
    }

    @Test
    void deveDetalharUmMedicoPeloSeuId() {
        Medico medico = getMedico();
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.of(medico));
        ResponseMedicoDTO responseMedicoDTO = medicoService.detalharMedico(id);
        assertNotNull(responseMedicoDTO, "Medico cadastrado não deve ser nulo.");
        Set<HorarioAtendimentoResponseDTO> horariosAtendimento = responseMedicoDTO.horariosAtendimento();
        assertAll("Dados do paciente retornado devem ser consistentes com os dados persistidos.",
                () -> assertEquals(id, responseMedicoDTO.id(), "Id deve ser o mesmo."),
                () -> assertEquals(nome, responseMedicoDTO.nome(), "Nome deve ser o mesmo."),
                () -> assertEquals(crm, responseMedicoDTO.crm(), "CRM deve ser o mesmo."),
                () -> assertEquals(email, responseMedicoDTO.email(), "Email deve ser o mesmo."),
                () -> assertEquals(ddd, responseMedicoDTO.telefone().ddd(), "DDD de telefone deve ser o mesmo."),
                () -> assertEquals(numeroTelefone, responseMedicoDTO.telefone().numero(), "Número de telefone deve ser o mesmo."),
                () -> assertEquals(logradouro, responseMedicoDTO.endereco().logradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEndereco, responseMedicoDTO.endereco().numero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairro, responseMedicoDTO.endereco().bairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, responseMedicoDTO.endereco().cidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, responseMedicoDTO.endereco().uf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, responseMedicoDTO.endereco().cep(), "CEP do endereço deve ser o mesmo."),
                () -> assertEquals(especializacao, responseMedicoDTO.especializacao(), "Especialização deve ser a mesma."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> diaDaSemana.equals(h.diaDaSemana())), "Dia da semana do horário de atendimento deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaInicial.equals(h.horaInicial())), "Hora inicial do horário de atendimento do endereço deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaFinal.equals(h.horaFinal())), "Hora final do horário de atendimento do endereço deve ser o mesmo.")
        );
    }

    @Test
    void deveRetornarUmaMedicoNaoEncontradoExceptionAoTentarDetalharUmMedicoNaoExistenteNoBancoDeDados() {
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.empty());
        assertThrows(MedicoNaoEncontradoException.class, () -> medicoService.detalharMedico(id));
    }

    @Test
    void deveAtualizarOsDadosDeUmMedico() {
        final String nomeAtualizado = "Nome Atualizado";
        final String especializacaoAtualizada = "OFTALMOLOGIA";
        final String dddAtualizado = "22";
        final String numeroTelefoneAtualizado = "222222222";
        final String logradouroAtualizado = "Av Atualizada";
        final String numeroEnderecoAtualizado = "33";
        final String bairroAtualizado = "Atualizado";
        Medico medico = getMedico();
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.of(medico));
        TelefoneRequestDTO telefoneAtualizado = new TelefoneRequestDTO(dddAtualizado, numeroTelefoneAtualizado);
        EnderecoRequestDTO enderecoAtualizado = new EnderecoRequestDTO(logradouroAtualizado, numeroEnderecoAtualizado, bairroAtualizado, cidade, uf, cep);
        RequestAtualizacaoMedicoDTO dadosAtualizacao = new RequestAtualizacaoMedicoDTO(nomeAtualizado, especializacaoAtualizada, telefoneAtualizado, enderecoAtualizado);
        ResponseMedicoDTO dadosMedicoAtualizado = medicoService.atualizar(id, dadosAtualizacao);
        assertNotNull(dadosMedicoAtualizado, "Medico cadastrado não deve ser nulo.");
        Set<HorarioAtendimentoResponseDTO> horariosAtendimento = dadosMedicoAtualizado.horariosAtendimento();
        assertAll("Dados que constam no request devem ser atualizados enquanto o restante deve ser mantido.",
                () -> assertEquals(id, dadosMedicoAtualizado.id(), "Id deve ser o mesmo."),
                () -> assertEquals(nomeAtualizado, dadosMedicoAtualizado.nome(), "Nome deve ser o atualizado."),
                () -> assertEquals(crm, dadosMedicoAtualizado.crm(), "CPF deve ser o mesmo."),
                () -> assertEquals(email, dadosMedicoAtualizado.email(), "Email deve ser o mesmo."),
                () -> assertEquals(dddAtualizado, dadosMedicoAtualizado.telefone().ddd(), "DDD de telefone deve ser atualizado."),
                () -> assertEquals(numeroTelefoneAtualizado, dadosMedicoAtualizado.telefone().numero(), "Número de telefone deve ser atualizado."),
                () -> assertEquals(logradouroAtualizado, dadosMedicoAtualizado.endereco().logradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEnderecoAtualizado, dadosMedicoAtualizado.endereco().numero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairroAtualizado, dadosMedicoAtualizado.endereco().bairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, dadosMedicoAtualizado.endereco().cidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, dadosMedicoAtualizado.endereco().uf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, dadosMedicoAtualizado.endereco().cep(), "CEP do endereço deve ser o mesmo."),
                () -> assertEquals(especializacaoAtualizada, dadosMedicoAtualizado.especializacao(), "Especialização deve ser a mesma."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> diaDaSemana.equals(h.diaDaSemana())), "Dia da semana do horário de atendimento deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaInicial.equals(h.horaInicial())), "Hora inicial do horário de atendimento do endereço deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaFinal.equals(h.horaFinal())), "Hora final do horário de atendimento do endereço deve ser o mesmo.")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveManterOsDadosAntigosAoTentarAtualizarOsDadosDoMedicoSemOsNovosDados(String dado) {
        Medico medico = getMedico();
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.of(medico));
        RequestAtualizacaoMedicoDTO dadosAtualizacao = new RequestAtualizacaoMedicoDTO(dado, dado, null, null);
        ResponseMedicoDTO dadosMedicoAtualizado = medicoService.atualizar(id, dadosAtualizacao);
        assertNotNull(dadosMedicoAtualizado, "Medico cadastrado não deve ser nulo.");
        Set<HorarioAtendimentoResponseDTO> horariosAtendimento = dadosMedicoAtualizado.horariosAtendimento();
        assertAll("Dados que constam no request devem ser atualizados enquanto o restante deve ser mantido.",
                () -> assertEquals(id, dadosMedicoAtualizado.id(), "Id deve ser o mesmo."),
                () -> assertEquals(nome, dadosMedicoAtualizado.nome(), "Nome deve ser o atualizado."),
                () -> assertEquals(crm, dadosMedicoAtualizado.crm(), "CPF deve ser o mesmo."),
                () -> assertEquals(email, dadosMedicoAtualizado.email(), "Email deve ser o mesmo."),
                () -> assertEquals(ddd, dadosMedicoAtualizado.telefone().ddd(), "DDD de telefone deve ser atualizado."),
                () -> assertEquals(numeroTelefone, dadosMedicoAtualizado.telefone().numero(), "Número de telefone deve ser atualizado."),
                () -> assertEquals(logradouro, dadosMedicoAtualizado.endereco().logradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEndereco, dadosMedicoAtualizado.endereco().numero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairro, dadosMedicoAtualizado.endereco().bairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, dadosMedicoAtualizado.endereco().cidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, dadosMedicoAtualizado.endereco().uf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, dadosMedicoAtualizado.endereco().cep(), "CEP do endereço deve ser o mesmo."),
                () -> assertEquals(especializacao, dadosMedicoAtualizado.especializacao(), "Especialização deve ser a mesma."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> diaDaSemana.equals(h.diaDaSemana())), "Dia da semana do horário de atendimento deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaInicial.equals(h.horaInicial())), "Hora inicial do horário de atendimento do endereço deve ser o mesmo."),
                () -> assertTrue(horariosAtendimento.stream().anyMatch(h -> horaFinal.equals(h.horaFinal())), "Hora final do horário de atendimento do endereço deve ser o mesmo.")
        );
    }

    @Test
    void deveRetornarUmaMedicoNaoEncontradoExceptionAoTentarAtualizarUmMedicoNaoExistenteNoBancoDeDados() {
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.empty());
        RequestAtualizacaoMedicoDTO dadosAtualizacao = mock(RequestAtualizacaoMedicoDTO.class);
        assertThrows(MedicoNaoEncontradoException.class, () -> medicoService.atualizar(id, dadosAtualizacao));
    }

    @Test
    void deveDeletarUmMedicoPeloSeuId() {
        Medico medico = getMedico();
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.of(medico));
        medicoService.deletar(id);
        verify(medicoRepositorySpy).deleteById(id);
    }

    @Test
    void deveRetornarUmaMedicoNaoEncontradoExceptionAoTentarDeletarUmMedicoNaoExistenteNoBancoDeDados() {
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.empty());
        assertThrows(MedicoNaoEncontradoException.class, () -> medicoService.deletar(id));
    }

    @Test
    void deveAtualizarOsHorariosDeAtendimentoDeUmMedico() {
        final String novoDiaDeAtendimento = "TERCA";
        final String novaHoraInicial = "09:00";
        final String novaHoraFinal = "17:00";
        Medico medico = getMedico();
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.of(medico));
        doNothing().when(horarioAtendimentoRepositorySpy).deleteByMedicoId(id);
        when(horarioAtendimentoRepositorySpy.saveAll(anySet())).thenReturn(anyList());
        HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO = new HorarioAtendimentoRequestDTO(novoDiaDeAtendimento, novaHoraInicial, novaHoraFinal);
        Set<HorarioAtendimentoRequestDTO> horariosAtendimento = Set.of(horarioAtendimentoRequestDTO);
        Set<HorarioAtendimentoResponseDTO> horariosAtendimentoAtualizado = medicoService.atualizarHorariosAtendimento(id, horariosAtendimento);
        assertNotNull(horariosAtendimentoAtualizado, "Horário de atendimento atualizado não deve ser nulo.");
        assertAll("Dados que constam no request devem ser atualizados.",
                () -> assertTrue(horariosAtendimentoAtualizado.stream().anyMatch(h -> novoDiaDeAtendimento.equals(h.diaDaSemana())), "Dia da semana do horário de atendimento deve ser atualizado."),
                () -> assertTrue(horariosAtendimentoAtualizado.stream().anyMatch(h -> novaHoraInicial.equals(h.horaInicial())), "Hora inicial do horário de atendimento do endereço deve ser atualizado."),
                () -> assertTrue(horariosAtendimentoAtualizado.stream().anyMatch(h -> novaHoraFinal.equals(h.horaFinal())), "Hora final do horário de atendimento do endereço deve ser atualizado.")
        );
    }

    @Test
    void deveRetornarUmaMedicoNaoEncontradoExceptionAoTentarAtualizarOsHorariosDeAtendimentoDeUmMedicoNaoExistenteNoBancoDeDados() {
        Set<HorarioAtendimentoRequestDTO> horariosAtendimento = Collections.emptySet();
        when(medicoRepositorySpy.findById(id)).thenReturn(Optional.empty());
        assertThrows(MedicoNaoEncontradoException.class, () -> medicoService.atualizarHorariosAtendimento(id, horariosAtendimento));
    }

    private RequestCadastroMedicoDTO getRequestCadastroMedicoDTO() {
        TelefoneRequestDTO telefoneDTO = new TelefoneRequestDTO(ddd, numeroTelefone);
        EnderecoRequestDTO enderecoDTO = new EnderecoRequestDTO(logradouro, numeroEndereco, bairro, cidade, uf, cep);
        Set<HorarioAtendimentoRequestDTO> horariosAtendimentoDTO = Set.of(new HorarioAtendimentoRequestDTO(diaDaSemana, horaInicial, horaFinal));
        return new RequestCadastroMedicoDTO(nome, crm, email, telefoneDTO, enderecoDTO, especializacao, horariosAtendimentoDTO);
    }

    private Medico getMedico() {
        Medico medico = Medico.builder()
                .nome(nome)
                .crm(new CRM(crm))
                .email(new Email(email))
                .telefone(Telefone.builder()
                        .ddd(ddd)
                        .numero(numeroTelefone)
                        .build())
                .endereco(Endereco.builder()
                        .logradouro(logradouro)
                        .numero(numeroEndereco)
                        .bairro(bairro)
                        .cidade(cidade)
                        .uf(uf)
                        .cep(cep)
                        .build())
                .especializacao(Especializacao.valueOf(especializacao))
                .build();
        HorarioAtendimento horarioAtendimento = HorarioAtendimento.builder()
                .medico(medico)
                .diaDaSemana(DiaDaSemana.valueOf(diaDaSemana))
                .horaInicial(LocalTime.parse(horaInicial))
                .horaFinal(LocalTime.parse(horaFinal))
                .build();
        Set<HorarioAtendimento> horariosAtendimento = Set.of(horarioAtendimento);
        setPrivateId(medico, id);
        medico.setHorariosAtendimento(horariosAtendimento);
        return medico;
    }
}