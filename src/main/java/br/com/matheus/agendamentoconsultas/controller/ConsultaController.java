package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.constraints.ValidStringDate;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaAgendadaDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaRequestDTO;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.*;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * <p>
 * Controlador REST para gerenciar agendamentos de consultas.
 * </p>
 * <p>
 * Este controlador fornece um endpoint para agendar e cancelar consultas,
 * bem como gerar relatórios dos agendamentos.
 * </p>
 *
 * @see br.com.matheus.agendamentoconsultas.service.ConsultaService
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Tag(
        name = "Consulta",
        description = "Serviço para gerenciar agendamentos de consultas.")
@Validated
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @Operation(
            summary = "Agendar consulta",
            description = "Agenda uma nova consulta no sistema.")
    @ApiResponse(
            responseCode = "201",
            description = "Consulta agendada com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ConsultaAgendadaDTO.class)))
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
    @ApiResponse(
            responseCode = "404",
            description = "Paciente não encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntidadeNaoEncontradaDTO.class)))
    @ApiResponse(
            responseCode = "422",
            description = "Consulta não foi agendada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ConsultaNaoAgendadaDTO.class)))
    @PostMapping("/agendamento")
    public ResponseEntity<ConsultaAgendadaDTO> agendar(
            @Valid @RequestBody ConsultaRequestDTO consultaRequestDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        Consulta consultaAgendada = consultaService.agendar(consultaRequestDTO);
        URI uri = uriComponentsBuilder.path("/consulta/{id}").buildAndExpand(consultaAgendada.getId()).toUri();
        ConsultaAgendadaDTO consultaAgendadaDTO = new ConsultaAgendadaDTO(consultaAgendada);
        return ResponseEntity.created(uri).body(consultaAgendadaDTO);
    }

    @Operation(
            summary = "Cancelar consulta",
            description = "Remove um consulta existente pelo seu ID")
    @ApiResponse(
            responseCode = "200",
            description = "Consulta cancelada")
    @ApiResponse(
            responseCode = "404",
            description = "Consulta não encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntidadeNaoEncontradaDTO.class)))
    @ApiResponse(
            responseCode = "403",
            description = "Consulta não pode mais ser cancelada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ConsultaNaoCanceladaDTO.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Relatório de consultas no dia.",
            description = "Retorna uma lista paginada de todas as consultas agendadas para um dia espeífico")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ConsultaAgendadaDTO.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FailedParameterRequestValidationDTO.class)))
    @GetMapping("/dia")
    public ResponseEntity<Page<ConsultaAgendadaDTO>> visualizarConsultasDoDia(
            @RequestParam @ValidStringDate(message = "Data inválida.") String dia,
            @Parameter(
                    description = "Informações de paginação e ordenação.",
                    schema = @Schema(
                            implementation = Pageable.class,
                            example = "{ \"page\": 0, \"size\": 20, \"sort\": [\"medico.nome\", \"horario\"] }"))
            @PageableDefault(size = 20, sort = {"medico.nome", "horario"}, direction = ASC) Pageable pageable) {
        Page<ConsultaAgendadaDTO> consultasNoDia = consultaService.visualizarConsultasDoDia(dia, pageable);
        return ResponseEntity.ok().body(consultasNoDia);
    }

    @Operation(
            summary = "Relatório de consultas no mês.",
            description = "Retorna uma lista paginada de todas as consultas agendadas para um mês espeífico")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ConsultaAgendadaDTO.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FailedParameterRequestValidationDTO.class)))
    @GetMapping("/mes")
    public ResponseEntity<Page<ConsultaAgendadaDTO>> visualizarConsultasDoMes(
            @RequestParam
            @Min(value = 1, message = "O valor do mês deve ser maior ou igual a 1")
            @Max(value = 12, message = "O valor do mês deve ser menor ou igual a 12")
            int mes,
            @RequestParam
            @Min(value = 1900, message = "O valor do ano deve ser maior ou igual a 1900")
            @Max(value = 9999, message = "O valor do ano deve ser menor ou igual a 9999")
            int ano,
            @Parameter(
                    description = "Informações de paginação e ordenação.",
                    schema = @Schema(
                            implementation = Pageable.class,
                            example = "{ \"page\": 0, \"size\": 20, \"sort\": [\"medico.nome\", \"horario\"] }"))
            @PageableDefault(size = 20, sort = {"medico.nome", "horario"}, direction = ASC)
            Pageable pageable) {
        Page<ConsultaAgendadaDTO> consultasNoMes = consultaService.visualizarConsultasDoMes(mes, ano, pageable);
        return ResponseEntity.ok().body(consultasNoMes);
    }
}