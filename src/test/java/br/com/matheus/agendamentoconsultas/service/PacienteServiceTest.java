package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.base.MockedUnitTest;
import br.com.matheus.agendamentoconsultas.controller.dto.*;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import br.com.matheus.agendamentoconsultas.model.vo.CPF;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.matheus.agendamentoconsultas.util.ReflectionUtil.setPrivateId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PacienteServiceTest extends MockedUnitTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Spy
    private PacienteRepository pacienteRepositorySpy;

    @Mock
    private Pageable pageableMock;

    private final Long id = 1L;
    private final String nome = "Nome";
    private final String cpf = "00000000000";
    private final String email = "email@email.com";
    private final String ddd = "11";
    private final String numeroTelefone = "111111111";
    private final String logradouro = "Rua Logradouro";
    private final String numeroEndereco = "11";
    private final String bairro = "Bairro";
    private final String cidade = "Cidade";
    private final String uf = "MG";
    private final String cep = "11111111";

    @Test
    void deveRetornarUmaPaginaDePacientesCadastrados() {
        List<Paciente> pacientes = Collections.singletonList(getPaciente());
        Page<Paciente> pacientesPage = new PageImpl<>(pacientes, pageableMock, pacientes.size());
        when(pacienteRepositorySpy.findAll(pageableMock)).thenReturn(pacientesPage);
        Page<ResponseTodosPacientesDTO> responsePacientesPage = pacienteService.visualizarTodos(pageableMock);
        assertAll("Retorno deve ser uma paginação válida.",
                () -> assertNotNull(responsePacientesPage, "Paginação não é nula."),
                () -> assertNotNull(responsePacientesPage.getContent().getFirst(), "O conteúdo da paginação não é nulo"),
                () -> assertEquals(1, responsePacientesPage.getTotalElements(), "O número total de elementos deve ser um."),
                () -> assertInstanceOf(ResponseTodosPacientesDTO.class, responsePacientesPage.getContent().getFirst(), "O elemento da paginação é uma instância de um ResponseTodosPacientes DTO.")
        );
    }

    @Test
    void deveCadastrarUmPacienteComDadosValidos() {
        when(pacienteRepositorySpy.save(any(Paciente.class))).thenAnswer(i -> i.getArgument(0));
        RequestCadastroPacienteDTO pacienteDTO = getRequestCadastroPacienteDTO();
        Paciente pacienteCadastrado = pacienteService.cadastrar(pacienteDTO);
        assertNotNull(pacienteCadastrado, "Paciente cadastrado não deve ser nulo.");
        assertAll("Dados do paciente persistido devem ser consistentes com o que foi recebido.",
                () -> assertEquals(nome, pacienteCadastrado.getNome(), "Nome deve ser o mesmo."),
                () -> assertEquals(cpf, pacienteCadastrado.getCpf().getValue(), "CPF deve ser o mesmo."),
                () -> assertEquals(email, pacienteCadastrado.getEmail().getValue(), "Email deve ser o mesmo."),
                () -> assertEquals(ddd, pacienteCadastrado.getTelefone().getDdd(), "DDD de telefone deve ser o mesmo."),
                () -> assertEquals(numeroTelefone, pacienteCadastrado.getTelefone().getNumero(), "Número de telefone deve ser o mesmo."),
                () -> assertEquals(logradouro, pacienteCadastrado.getEndereco().getLogradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEndereco, pacienteCadastrado.getEndereco().getNumero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairro, pacienteCadastrado.getEndereco().getBairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, pacienteCadastrado.getEndereco().getCidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, pacienteCadastrado.getEndereco().getUf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, pacienteCadastrado.getEndereco().getCep(), "CEP do endereço deve ser o mesmo.")
        );
    }

    @Test
    void deveDetalharUmPacientePeloSeuId() {
        Paciente paciente = getPaciente();
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.of(paciente));
        ResponsePacienteDTO responsePacienteDTO = pacienteService.detalharPaciente(id);
        assertNotNull(responsePacienteDTO, "Paciente cadastrado não deve ser nulo.");
        assertAll("Dados do paciente retornado devem ser consistentes com os dados persistidos.",
                () -> assertEquals(id, responsePacienteDTO.id(), "Id deve ser o mesmo."),
                () -> assertEquals(nome, responsePacienteDTO.nome(), "Nome deve ser o mesmo."),
                () -> assertEquals(cpf, responsePacienteDTO.cpf(), "CPF deve ser o mesmo."),
                () -> assertEquals(email, responsePacienteDTO.email(), "Email deve ser o mesmo."),
                () -> assertEquals(ddd, responsePacienteDTO.telefone().ddd(), "DDD de telefone deve ser o mesmo."),
                () -> assertEquals(numeroTelefone, responsePacienteDTO.telefone().numero(), "Número de telefone deve ser o mesmo."),
                () -> assertEquals(logradouro, responsePacienteDTO.endereco().logradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEndereco, responsePacienteDTO.endereco().numero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairro, responsePacienteDTO.endereco().bairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, responsePacienteDTO.endereco().cidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, responsePacienteDTO.endereco().uf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, responsePacienteDTO.endereco().cep(), "CEP do endereço deve ser o mesmo.")
        );
    }

    @Test
    void deveRetornarUmaPacienteNaoEncontradoExceptionAoTentarDetalharUmPacienteNaoExistenteNoBancoDeDados() {
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.empty());
        assertThrows(PacienteNaoEncontradoException.class, () -> pacienteService.detalharPaciente(id));
    }

    @Test
    void deveAtualizarOsDadosDeUmPaciente() {
        final String nomeAtualizado = "Nome Atualizado";
        final String dddAtualizado = "22";
        final String numeroTelefoneAtualizado = "222222222";
        final String logradouroAtualizado = "Av Atualizada";
        final String numeroEnderecoAtualizado = "33";
        final String bairroAtualizado = "Atualizado";
        Paciente paciente = getPaciente();
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.of(paciente));
        TelefoneRequestDTO telefoneAtualizado = new TelefoneRequestDTO(dddAtualizado, numeroTelefoneAtualizado);
        EnderecoRequestDTO enderecoAtualizado = new EnderecoRequestDTO(logradouroAtualizado, numeroEnderecoAtualizado, bairroAtualizado, cidade, uf, cep);
        RequestAtualizacaoPacienteDTO dadosAtualizacao = new RequestAtualizacaoPacienteDTO(nomeAtualizado, telefoneAtualizado, enderecoAtualizado);
        ResponsePacienteDTO dadosPacienteAtualizado = pacienteService.atualizar(id, dadosAtualizacao);
        assertNotNull(dadosPacienteAtualizado, "Paciente cadastrado não deve ser nulo.");
        assertAll("Dados que constam no request devem ser atualizados enquanto o restante deve ser mantido.",
                () -> assertEquals(id, dadosPacienteAtualizado.id(), "Id deve ser o mesmo."),
                () -> assertEquals(nomeAtualizado, dadosPacienteAtualizado.nome(), "Nome deve ser o atualizado."),
                () -> assertEquals(cpf, dadosPacienteAtualizado.cpf(), "CPF deve ser o mesmo."),
                () -> assertEquals(email, dadosPacienteAtualizado.email(), "Email deve ser o mesmo."),
                () -> assertEquals(dddAtualizado, dadosPacienteAtualizado.telefone().ddd(), "DDD de telefone deve ser atualizado."),
                () -> assertEquals(numeroTelefoneAtualizado, dadosPacienteAtualizado.telefone().numero(), "Número de telefone deve ser atualizado."),
                () -> assertEquals(logradouroAtualizado, dadosPacienteAtualizado.endereco().logradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEnderecoAtualizado, dadosPacienteAtualizado.endereco().numero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairroAtualizado, dadosPacienteAtualizado.endereco().bairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, dadosPacienteAtualizado.endereco().cidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, dadosPacienteAtualizado.endereco().uf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, dadosPacienteAtualizado.endereco().cep(), "CEP do endereço deve ser o mesmo.")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveManterOsDadosAntigosAoTentarAtualizarOsDadosDoMedicoSemOsNovosDados(String dado) {
        Paciente paciente = getPaciente();
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.of(paciente));
        RequestAtualizacaoPacienteDTO dadosAtualizacao = new RequestAtualizacaoPacienteDTO(dado, null, null);
        ResponsePacienteDTO dadosPacienteAtualizado = pacienteService.atualizar(id, dadosAtualizacao);
        assertNotNull(dadosPacienteAtualizado, "Paciente cadastrado não deve ser nulo.");
        assertAll("Dados que constam no request devem ser atualizados enquanto o restante deve ser mantido.",
                () -> assertEquals(id, dadosPacienteAtualizado.id(), "Id deve ser o mesmo."),
                () -> assertEquals(nome, dadosPacienteAtualizado.nome(), "Nome deve ser o atualizado."),
                () -> assertEquals(cpf, dadosPacienteAtualizado.cpf(), "CPF deve ser o mesmo."),
                () -> assertEquals(email, dadosPacienteAtualizado.email(), "Email deve ser o mesmo."),
                () -> assertEquals(ddd, dadosPacienteAtualizado.telefone().ddd(), "DDD de telefone deve ser atualizado."),
                () -> assertEquals(numeroTelefone, dadosPacienteAtualizado.telefone().numero(), "Número de telefone deve ser atualizado."),
                () -> assertEquals(logradouro, dadosPacienteAtualizado.endereco().logradouro(), "Logradouro do endereço deve ser o mesmo."),
                () -> assertEquals(numeroEndereco, dadosPacienteAtualizado.endereco().numero(), "Número do endereço deve ser o mesmo."),
                () -> assertEquals(bairro, dadosPacienteAtualizado.endereco().bairro(), "Bairro do endereço deve ser o mesmo."),
                () -> assertEquals(cidade, dadosPacienteAtualizado.endereco().cidade(), "Cidade do endereço deve ser a mesma."),
                () -> assertEquals(uf, dadosPacienteAtualizado.endereco().uf(), "UF do endereço deve ser a mesma."),
                () -> assertEquals(cep, dadosPacienteAtualizado.endereco().cep(), "CEP do endereço deve ser o mesmo.")
        );
    }

    @Test
    void deveRetornarUmaPacienteNaoEncontradoExceptionAoTentarAtualizarUmPacienteNaoExistenteNoBancoDeDados() {
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.empty());
        RequestAtualizacaoPacienteDTO dadosAtualizacao = mock(RequestAtualizacaoPacienteDTO.class);
        assertThrows(PacienteNaoEncontradoException.class, () -> pacienteService.atualizar(id, dadosAtualizacao));
    }

    @Test
    void deveDeletarUmPacientePeloSeuId() {
        Paciente paciente = getPaciente();
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.of(paciente));
        pacienteService.deletar(id);
        verify(pacienteRepositorySpy).deleteById(id);
    }

    @Test
    void deveRetornarUmaPacienteNaoEncontradoExceptionAoTentarDeletarUmPacienteNaoExistenteNoBancoDeDados() {
        when(pacienteRepositorySpy.findById(id)).thenReturn(Optional.empty());
        assertThrows(PacienteNaoEncontradoException.class, () -> pacienteService.deletar(id));
    }

    private Paciente getPaciente() {
        CPF cpfEntity = new CPF(cpf);
        Email emailEntity = new Email(email);
        Telefone telefone = new Telefone(ddd, numeroTelefone);
        Endereco endereco = new Endereco(logradouro, numeroEndereco, bairro, cidade, uf, cep);
        Paciente paciente = new Paciente(nome, cpfEntity, emailEntity, telefone, endereco);
        setPrivateId(paciente, id);
        return paciente;
    }

    private RequestCadastroPacienteDTO getRequestCadastroPacienteDTO() {
        TelefoneRequestDTO telefoneDTO = new TelefoneRequestDTO(ddd, numeroTelefone);
        EnderecoRequestDTO enderecoDTO = new EnderecoRequestDTO(logradouro, numeroEndereco, bairro, cidade, uf, cep);
        return new RequestCadastroPacienteDTO(nome, cpf, email, telefoneDTO, enderecoDTO);
    }
}