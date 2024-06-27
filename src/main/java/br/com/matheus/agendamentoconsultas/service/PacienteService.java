package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.*;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import br.com.matheus.agendamentoconsultas.model.vo.CPF;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

/**
 * <p>
 * Serviço para operações relacionadas a pacientes.
 * </p>
 * <p>
 * Este serviço gerencia operações de CRUD e outras operações relacionadas aos pacientes,
 * como cadastro, atualização, exclusão, e consulta de pacientes.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Retorna uma paginaçãp de todos os pacientes cadastrados.
     *
     * @param pageable As informações da página solicitada.
     * @return Uma pagniação de pacientes.
     */
    public Page<ResponseTodosPacientesDTO> visualizarTodos(Pageable pageable) {
        Page<Paciente> pacientes = this.pacienteRepository.findAll(pageable);
        return pacientes.map(ResponseTodosPacientesDTO::new);
    }

    /**
     * Cadastra um novo paciente com base nos dados fornecidos.
     *
     * @param pacienteDTO Os dados do paciente a ser cadastrado.
     * @return O paciente cadastrado.
     */
    @Transactional
    public Paciente cadastrar(RequestCadastroPacienteDTO pacienteDTO) {
        TelefoneRequestDTO telefoneDTO = pacienteDTO.telefone();
        EnderecoRequestDTO enderecoDTO = pacienteDTO.endereco();
        Paciente paciente = Paciente.builder()
                .nome(pacienteDTO.nome())
                .cpf(new CPF(pacienteDTO.cpf()))
                .email(new Email(pacienteDTO.email()))
                .telefone(new Telefone(telefoneDTO.ddd(), telefoneDTO.numero()))
                .endereco(new Endereco(enderecoDTO.logradouro(), enderecoDTO.numero(), enderecoDTO.bairro(),
                        enderecoDTO.cidade(), enderecoDTO.uf(), enderecoDTO.cep()))
                .build();
        return this.pacienteRepository.save(paciente);
    }

    /**
     * Detalha as informações de um paciente específico com base no ID fornecido.
     *
     * @param id O ID do paciente a ser detalhado.
     * @return As informações detalhadas do paciente.
     * @throws PacienteNaoEncontradoException Se o paciente com o ID especificado não for encontrado.
     */
    public ResponsePacienteDTO detalharPaciente(Long id) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        return new ResponsePacienteDTO(paciente);
    }

    /**
     * Atualiza as informações de um paciente existente com base no ID e nos dados fornecidos.
     *
     * @param id O ID do paciente a ser atualizado.
     * @param dadosAtualizacao Os novos dados a serem atualizados para o paciente.
     * @return As informações atualizadas do paciente.
     * @throws PacienteNaoEncontradoException Se o paciente com o ID especificado não for encontrado.
     */
    @Transactional
    public ResponsePacienteDTO atualizar(Long id, RequestAtualizacaoPacienteDTO dadosAtualizacao) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        ofNullable(dadosAtualizacao.nome()).filter(n -> !n.isBlank()).ifPresent(paciente::setNome);
        ofNullable(dadosAtualizacao.telefone()).ifPresent(t -> paciente.setTelefone(new Telefone(t.ddd(), t.numero())));
        ofNullable(dadosAtualizacao.endereco()).ifPresent(e -> paciente.setEndereco(new Endereco(e.logradouro(),
                e.numero(), e.bairro(), e.cidade(), e.uf(), e.cep())));
        return new ResponsePacienteDTO(paciente);
    }

    /**
     * Deleta um paciente existente com base no ID fornecido.
     *
     * @param id O ID do paciente a ser deletado.
     * @throws PacienteNaoEncontradoException Se o paciente com o ID especificado não for encontrado.
     */
    @Transactional
    public void deletar(Long id) {
        Long idPaciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new).getId();
        this.pacienteRepository.deleteById(idPaciente);
    }
}