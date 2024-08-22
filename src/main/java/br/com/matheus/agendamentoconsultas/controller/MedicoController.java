package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.controller.dto.*;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.EntidadeNaoEncontradaDTO;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.FailedFieldRequestValidationDTO;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.service.MedicoService;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * <p>
 * Controlador REST para gerenciar informações dos médicos.
 * </p>
 * <p>
 * Este controlador fornece endpoints para visualizar, cadastrar, detalhar, atualizar e deletar pacientes.
 * </p>
 *
 * @see br.com.matheus.agendamentoconsultas.service.MedicoService
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Tag(
        name = "Médico",
        description = "Serviço para gerenciar informações dos médicos.")
@Validated
@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(
            summary = "Visualizar todos os médicos",
            description = "Retorna uma lista paginada de todos os médicos")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseTodosMedicosDTO.class)))
    @GetMapping
    public ResponseEntity<Page<ResponseTodosMedicosDTO>> visualizarTodos(
            @Parameter(
                    description = "Informações de paginação e ordenação.",
                    schema = @Schema(
                            implementation = Pageable.class,
                            example = "{ \"page\": 0, \"size\": 20, \"sort\": \"nome,ASC\" }"))
            @PageableDefault(size = 20, sort = "nome", direction = ASC) Pageable pageable) {
        Page<ResponseTodosMedicosDTO> medicos = this.medicoService.visualizarTodos(pageable);
        return ResponseEntity.ok().body(medicos);
    }

    @Operation(
            summary = "Cadastrar médico",
            description = "Cadastra um novo médico no sistema.")
    @ApiResponse(
            responseCode = "201",
            description = "Médico Criado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMedicoDTO.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FailedFieldRequestValidationDTO.class)))
    @PostMapping
    public ResponseEntity<ResponseMedicoDTO> cadastrar(
            @RequestBody @Valid RequestCadastroMedicoDTO requestCadastroMedicoDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = this.medicoService.cadastrar(requestCadastroMedicoDTO);
        URI uri = uriComponentsBuilder.path("medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseMedicoDTO(medico));
    }

    @Operation(
            summary = "Detalhar médico",
            description = "Retorna os detalhes de um médico específico pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMedicoDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Médico não encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntidadeNaoEncontradaDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMedicoDTO> detalharMedico(@PathVariable Long id) {
        ResponseMedicoDTO detalhesMedico = this.medicoService.detalharMedico(id);
        return ResponseEntity.ok(detalhesMedico);
    }

    @Operation(
            summary = "Atualizar médico",
            description = "Atualiza as informações de um médico existente pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Médico atualizado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMedicoDTO.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FailedFieldRequestValidationDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Médico não encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntidadeNaoEncontradaDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMedicoDTO> atualizar(@PathVariable Long id,
                                                       @RequestBody @Valid RequestAtualizacaoMedicoDTO requestAtualizacaoMedicoDTO) {
        ResponseMedicoDTO medicoAtualizado = this.medicoService.atualizar(id, requestAtualizacaoMedicoDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @Operation(
            summary = "Deletar médico",
            description = "Remove um médico existente pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Médico deletado")
    @ApiResponse(
            responseCode = "404",
            description = "Médico não encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntidadeNaoEncontradaDTO.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.medicoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Atualizar horários de atendimento de um médico",
            description = "Atualiza os horários de atendimento de um médico existente pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Horários de atendimento atualizados",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = HorarioAtendimentoResponseDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Médico não encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntidadeNaoEncontradaDTO.class)))
    @PutMapping("/{id}/horarios-atendimento")
    public ResponseEntity<Set<HorarioAtendimentoResponseDTO>> atualizarHorariosAtendimento(@PathVariable Long id, @RequestBody @Valid Set<HorarioAtendimentoRequestDTO> horariosAtendimentoRequestDTO) {
        Set<HorarioAtendimentoResponseDTO> horariosAtendimento = medicoService.atualizarHorariosAtendimento(id, horariosAtendimentoRequestDTO);
        return ResponseEntity.ok(horariosAtendimento);
    }
}