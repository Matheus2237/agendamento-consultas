package br.com.matheus.agendamentoconsultas.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.matheus.agendamentoconsultas.controller.dto.PacienteDto;
import br.com.matheus.agendamentoconsultas.controller.dto.VisualizarTodosPacientesDto;
import br.com.matheus.agendamentoconsultas.controller.form.AtualizacaoPacienteForm;
import br.com.matheus.agendamentoconsultas.controller.form.PacienteForm;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;
		
	@GetMapping
	public ResponseEntity<Page<VisualizarTodosPacientesDto>> visualizarTodos(@PageableDefault(page = 0, size = 20, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		Page<Paciente> pacientes = this.pacienteRepository.findAll(pageable);
		return ResponseEntity.ok().body(pacientes.map(VisualizarTodosPacientesDto::new));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PacienteDto> cadastrar(@RequestBody @Valid PacienteForm pacienteForm, UriComponentsBuilder uriComponentsBuilder) {
		Paciente paciente = pacienteForm.toPaciente();
		this.pacienteRepository.save(paciente);
		URI uri = uriComponentsBuilder.path("paciente/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new PacienteDto(paciente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> datalharPaciente(@PathVariable Long id) {
		Optional<Paciente> pacienteOptional = this.pacienteRepository.findById(id);
		if (pacienteOptional.isPresent()) {
			return ResponseEntity.ok(new PacienteDto(pacienteOptional.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPacienteForm atualizacaoPacienteForm) {
		Optional<Paciente> pacienteOptional = this.pacienteRepository.findById(id);
		if (pacienteOptional.isPresent()) {
			Paciente paciente = atualizacaoPacienteForm.atualizar(id, pacienteRepository);
			return ResponseEntity.ok(new PacienteDto(paciente));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		Optional<Paciente> pacienteOptional = this.pacienteRepository.findById(id);
		if (pacienteOptional.isPresent()) {
			this.pacienteRepository.deleteById(id);
			return ResponseEntity.ok().body("Paciente excluído com sucesso.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
	}
}
