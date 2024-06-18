package br.com.matheus.agendamentoconsultas.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record TelefoneRequestDTO(

        @NotBlank(message = "O campo 'ddd' é obrigatório")
        String ddd,

        @NotBlank(message = "O campo 'número' é obrigatório")
        String numero
) {}