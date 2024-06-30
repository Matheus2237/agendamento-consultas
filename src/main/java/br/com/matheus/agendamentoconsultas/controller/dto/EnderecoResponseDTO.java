package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de endereço.
 *
 * @param logradouro O logradouro do endereço.
 * @param numero     O número do endereço.
 * @param bairro     O bairro do endereço.
 * @param cidade     A cidade do endereço.
 * @param uf         A unidade federativa (UF) do endereço.
 * @param cep        O CEP do endereço.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para resposta de endereço")
public record EnderecoResponseDTO(

        @Schema(description = "Logradouro do endereço", example = "Rua Professor Luiz")
        String logradouro,

        @Schema(description = "Número do endereço", example = "11, apto 1")
        String numero,

        @Schema(description = "Bairro do endereço", example = "Centro")
        String bairro,

        @Schema(description = "Cidade do endereço", example = "São Paulo")
        String cidade,

        @Schema(description = "Unidade Federativa do endereço", example = "SP")
        String uf,

        @Schema(description = "CEP do endereço", example = "11222123")
        String cep
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de endereço.
     *
     * @param endereco A entidade de endereço.
     */
    public EnderecoResponseDTO(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(),
                endereco.getCidade(), endereco.getUf(), endereco.getCep());
    }
}