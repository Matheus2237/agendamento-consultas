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

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Page<ResponseTodosPacientesDTO> visualizarTodos(Pageable pageable) {
        Page<Paciente> pacientes = this.pacienteRepository.findAll(pageable);
        return pacientes.map(ResponseTodosPacientesDTO::new);
    }

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

    public ResponsePacienteDTO detalharPaciente(Long id) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        return new ResponsePacienteDTO(paciente);
    }

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

    @Transactional
    public void deletar(Long id) {
        Long idPaciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new).getId();
        this.pacienteRepository.deleteById(idPaciente);
    }
}