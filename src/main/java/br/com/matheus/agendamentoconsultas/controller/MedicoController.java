package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.controller.dto.RequestAtualizacaoMedicoDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroMedicoDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseMedicoDto;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseTodosMedicosDto;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.service.MedicoService;
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
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<Page<ResponseTodosMedicosDto>> visualizarTodos(@PageableDefault(size = 20, sort = "nome", direction = Direction.ASC) Pageable pageable) {
        Page<ResponseTodosMedicosDto> medicos = this.medicoService.visualizarTodos(pageable);
        return ResponseEntity.ok().body(medicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseMedicoDto> cadastrar(@RequestBody @Valid RequestCadastroMedicoDTO requestCadastroMedicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = this.medicoService.cadastrar(requestCadastroMedicoDTO);
        URI uri = uriComponentsBuilder.path("medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseMedicoDto(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMedicoDto> detalharMedico(@PathVariable Long id) {
        ResponseMedicoDto detalhesMedico = this.medicoService.detalharMedico(id);
        return ResponseEntity.ok(detalhesMedico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseMedicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid RequestAtualizacaoMedicoDTO requestAtualizacaoMedicoDTO) {
        ResponseMedicoDto medicoAtualizado = this.medicoService.atualizar(id, requestAtualizacaoMedicoDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        this.medicoService.deletar(id);
        return ResponseEntity.ok().body("Médico excluído com sucesso.");
    }
}
