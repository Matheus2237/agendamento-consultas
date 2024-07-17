package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.controller.dto.RequestAtualizacaoPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroPacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponsePacienteDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseTodosPacientesDTO;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.FailedFieldRequestValidationDTO;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * <p>
 * Controlador REST para gerenciar informações dos pacientes.
 * </p>
 * <p>
 * Este controlador fornece endpoints para visualizar, cadastrar, detalhar, atualizar e deletar pacientes.
 * </p>
 *
 * @see br.com.matheus.agendamentoconsultas.service.PacienteService
 */
@Tag(
        name = "Paciente",
        description = "Serviço para gerenciar informações dos pacientes.")
@Validated
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(
            summary = "Visualizar todos os pacientes",
            description = "Retorna uma lista paginada de todos os pacientes")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseTodosPacientesDTO.class)))
    @GetMapping
    public ResponseEntity<Page<ResponseTodosPacientesDTO>> visualizarTodos(
            @Parameter(
                    description = "Informações de paginação e ordenação.",
                    schema = @Schema(
                            implementation = Pageable.class,
                            example = "{ \"page\": 0, \"size\": 20, \"sort\": \"nome,ASC\" }"))
            @PageableDefault(size = 20, sort = "nome", direction = Direction.ASC) Pageable pageable) {
        Page<ResponseTodosPacientesDTO> pacientes = this.pacienteService.visualizarTodos(pageable);
        return ResponseEntity.ok().body(pacientes);
    }

    @Operation(
            summary = "Cadastrar paciente",
            description = "Cadastra um novo paciente no sistema.")
    @ApiResponse(
            responseCode = "201",
            description = "Paciente Criado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponsePacienteDTO.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FailedFieldRequestValidationDTO.class)))
    @PostMapping
    public ResponseEntity<ResponsePacienteDTO> cadastrar(
            @RequestBody @Valid RequestCadastroPacienteDTO requestCadastroPacienteDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = this.pacienteService.cadastrar(requestCadastroPacienteDTO);
        URI uri = uriComponentsBuilder.path("paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponsePacienteDTO(paciente));
    }

    @Operation(
            summary = "Detalhar paciente",
            description = "Retorna os detalhes de um paciente específico pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponsePacienteDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Paciente não encontrado",
            content = @Content(mediaType = "string"))
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePacienteDTO> datalharPaciente(@PathVariable Long id) {
        ResponsePacienteDTO detalhesPaciente = this.pacienteService.detalharPaciente(id);
        return ResponseEntity.ok(detalhesPaciente);
    }

    @Operation(
            summary = "Atualizar paciente",
            description = "Atualiza as informações de um paciente existente pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Paciente atualizado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponsePacienteDTO.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FailedFieldRequestValidationDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Paciente não encontrado",
            content = @Content(mediaType = "string"))
    @PutMapping("/{id}")
    public ResponseEntity<ResponsePacienteDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid RequestAtualizacaoPacienteDTO requestAtualizacaoPacienteDTO) {
        ResponsePacienteDTO pacienteAtualizado = this.pacienteService.atualizar(id, requestAtualizacaoPacienteDTO);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @Operation(
            summary = "Deletar paciente",
            description = "Remove um paciente existente pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Paciente deletado")
    @ApiResponse(
            responseCode = "404",
            description = "Paciente não encontrado",
            content = @Content(mediaType = "string"))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.pacienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}