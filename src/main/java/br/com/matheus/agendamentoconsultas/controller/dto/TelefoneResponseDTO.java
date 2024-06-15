package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Telefone;

public record TelefoneResponseDTO(String ddd, String numero) {

    public TelefoneResponseDTO(Telefone telefone) {
        this(telefone.getDdd(), telefone.getNumero());
    }
}