package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Telefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneRequestDTO(

        @NotBlank(message = "O campo 'ddd' é obrigatório")
        String ddd,

        @NotBlank(message = "O campo 'número' é obrigatório")
        String numero
) {

    public Telefone toModel() {
        return new Telefone(ddd, numero);
    }
}