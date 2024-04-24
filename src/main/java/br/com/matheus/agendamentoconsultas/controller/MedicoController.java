package br.com.matheus.agendamentoconsultas.controller;

import java.net.URI;
import java.util.Optional;

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

import br.com.matheus.agendamentoconsultas.controller.dto.MedicoDto;
import br.com.matheus.agendamentoconsultas.controller.dto.VisualizarTodosMedicoDto;
import br.com.matheus.agendamentoconsultas.controller.form.AtualizacaoMedicoForm;
import br.com.matheus.agendamentoconsultas.controller.form.MedicoForm;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@GetMapping
	public ResponseEntity<Page<VisualizarTodosMedicoDto>> visualizarTodos(@PageableDefault(page = 0, size = 20, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		Page<Medico> medicos = this.medicoRepository.findAll(pageable);
		return ResponseEntity.ok().body(medicos.map(VisualizarTodosMedicoDto::new));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MedicoDto> cadastrar(@RequestBody @Valid MedicoForm medicoForm, UriComponentsBuilder uriComponentsBuilder) {
		Medico medico = medicoForm.toMedico();
		this.medicoRepository.save(medico);
		URI uri = uriComponentsBuilder.path("medico/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new MedicoDto(medico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalharMedico(@PathVariable Long id) {
		Optional<Medico> medicoOptional = this.medicoRepository.findById(id);
		if (medicoOptional.isPresent()) {
			return ResponseEntity.ok(new MedicoDto(medicoOptional.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMedicoForm atualizacaoMedicoForm) {
		Optional<Medico> medicoOptional = this.medicoRepository.findById(id);
		if (medicoOptional.isPresent()) {
			Medico medico = atualizacaoMedicoForm.atualizar(id, medicoRepository);
			return ResponseEntity.ok(new MedicoDto(medico));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		Optional<Medico> medicoOptional = this.medicoRepository.findById(id);
		if (medicoOptional.isPresent()) {
			this.medicoRepository.deleteById(id);
			return ResponseEntity.ok().body("Médico excluído com sucesso.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
	}
}
