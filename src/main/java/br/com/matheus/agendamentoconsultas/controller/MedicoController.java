package br.com.matheus.agendamentoconsultas.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@GetMapping
	public List<VisualizarTodosMedicoDto> visualizarTodos() {
		List<Medico> medicos = medicoRepository.findAll();
		return VisualizarTodosMedicoDto.converter(medicos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MedicoDto> cadastrar(@RequestBody @Valid MedicoForm medicoForm, UriComponentsBuilder uriComponentsBuilder) {
		Medico medico = medicoForm.toMedico();
		medicoRepository.save(medico);
		URI uri = uriComponentsBuilder.path("medico/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new MedicoDto(medico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoDto> detalharMedico(@PathVariable Long id) {
		Optional<Medico> medico = medicoRepository.findById(id);
		if (medico.isPresent()) {
			return ResponseEntity.ok(new MedicoDto(medico.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MedicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMedicoForm atualizacaoMedicoForm) {
		Optional<Medico> optional = medicoRepository.findById(id);
		if (optional.isPresent()) {
			Medico medico = atualizacaoMedicoForm.atualizar(id, medicoRepository);
			return ResponseEntity.ok(new MedicoDto(medico));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<MedicoDto> deletar(@PathVariable Long id) {
		Optional<Medico> medico = medicoRepository.findById(id);
		if (medico.isPresent()) {
			medicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
