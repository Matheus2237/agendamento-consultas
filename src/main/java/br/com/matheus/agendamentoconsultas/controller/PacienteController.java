package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.controller.dto.RequestAtualizacaoPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponsePacienteDto;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseTodosPacientesDto;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	private final PacienteService pacienteService;

	@Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
	public ResponseEntity<Page<ResponseTodosPacientesDto>> visualizarTodos(@PageableDefault(size = 20, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		Page<ResponseTodosPacientesDto> pacientes = this.pacienteService.visualizarTodos(pageable);
		return ResponseEntity.ok().body(pacientes);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ResponsePacienteDto> cadastrar(@RequestBody @Valid RequestCadastroPacienteDTO requestCadastroPacienteDTO, UriComponentsBuilder uriComponentsBuilder) {
		Paciente paciente = this.pacienteService.cadastrar(requestCadastroPacienteDTO);
		URI uri = uriComponentsBuilder.path("paciente/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ResponsePacienteDto(paciente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> datalharPaciente(@PathVariable Long id) {
		ResponsePacienteDto detalhesPaciente = this.pacienteService.detalharPaciente(id);
		return ResponseEntity.ok(detalhesPaciente);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid RequestAtualizacaoPacienteDTO requestAtualizacaoPacienteDTO) {
		ResponsePacienteDto pacienteAtualizado = this.pacienteService.atualizar(id, requestAtualizacaoPacienteDTO);
		return ResponseEntity.ok(pacienteAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		this.pacienteService.deletar(id);
		return ResponseEntity.ok().body("Paciente excluído com sucesso.");
	}
}
