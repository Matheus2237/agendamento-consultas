package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.RequestAtualizacaoPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponsePacienteDto;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseTodosPacientesDto;
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

    public Page<ResponseTodosPacientesDto> visualizarTodos(Pageable pageable) {
        Page<Paciente> pacientes = this.pacienteRepository.findAll(pageable);
        return pacientes.map(ResponseTodosPacientesDto::new);
    }

    public Paciente cadastrar(RequestCadastroPacienteDTO requestCadastroPacienteDTO) {
        Paciente paciente = requestCadastroPacienteDTO.toPaciente();
        return this.pacienteRepository.save(paciente);
    }

    public ResponsePacienteDto detalharPaciente(Long id) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        return new ResponsePacienteDto(paciente);
    }

    public ResponsePacienteDto atualizar(Long id, RequestAtualizacaoPacienteDTO atualizacao) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);
        atualizacao.atualizar(paciente);
        return new ResponsePacienteDto(paciente);
    }

    public void deletar(Long id) {
        Long idPaciente = this.pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new).getId();
        this.pacienteRepository.deleteById(idPaciente);
    }
}
