package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaAgendadaDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ConsultaRequestDTO;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.ConsultaNaoAgendadaDTO;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.FailedRequestValidationDTO;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
 */
@Tag(
        name = "Consulta",
        description = "Serviço para gerenciar agendamentos de consultas.")
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    /**
     * <p>
     * Endpoint para agendar uma consulta.
     * </p>
     * <p>
     * Este endpoint permite agendar uma nova consulta no sistema.
     * </p>
     *
     * @param consultaRequestDTO Objeto contendo as informações da consulta a ser agendada.
     * @return Objeto contendo as informações da consulta agendada.
     */
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
                    schema = @Schema(implementation = FailedRequestValidationDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Médico não encontrado",
            content = @Content(mediaType = "string"))
    @ApiResponse(
            responseCode = "404",
            description = "Paciente não encontrado",
            content = @Content(mediaType = "string"))
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
}