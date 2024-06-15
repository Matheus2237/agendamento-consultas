package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(

        @NotBlank(message = "O campo 'logradouro' é obrigatório")
        String logradouro,

        @NotBlank(message = "O campo 'numero' é obrigatório")
        String numero,

        @NotBlank(message = "O campo 'bairro' é obrigatório")
        String bairro,

        @NotBlank(message = "O campo 'cidade' é obrigatório")
        String cidade,

        @NotBlank(message = "O campo 'uf' é obrigatório")
        String uf,

        @NotBlank(message = "O campo 'cep' é obrigatório")
        String cep
) {

    public Endereco toModel() {
        return new Endereco(logradouro, numero, bairro, cidade, uf, cep);
    }
}