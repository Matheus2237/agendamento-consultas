package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.RequestAtualizacaoPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponsePacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseTodosPacientesDTO;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Paciente cadastrar(RequestCadastroPacienteDTO requestCadastroPacienteDTO) {
        Paciente paciente = requestCadastroPacienteDTO.toPaciente();
        return this.pacienteRepository.save(paciente);
    }

    public ResponsePacienteDTO detalharPaciente(Long id) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        return new ResponsePacienteDTO(paciente);
    }

    public ResponsePacienteDTO atualizar(Long id, RequestAtualizacaoPacienteDTO atualizacao) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        atualizacao.atualizar(paciente);
        return new ResponsePacienteDTO(paciente);
    }

    public void deletar(Long id) {
        Long idPaciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new).getId();
        this.pacienteRepository.deleteById(idPaciente);
    }
}
