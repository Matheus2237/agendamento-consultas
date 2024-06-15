package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Endereco;

public record EnderecoResponseDTO(
        String logradouro, String numero, String bairro, String cidade, String uf, String cep) {

    public EnderecoResponseDTO(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(),
                endereco.getCidade(), endereco.getUf(), endereco.getCep());
    }
}