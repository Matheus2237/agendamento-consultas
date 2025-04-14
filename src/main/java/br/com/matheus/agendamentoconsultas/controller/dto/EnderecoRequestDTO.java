package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para solicitação de endereço.
 *
 * @param logradouro O logradouro do endereço. Não pode ser vazio ou nulo.
 * @param numero     O número do endereço. Não pode ser vazio ou nulo.
 * @param bairro     O bairro do endereço. Não pode ser vazio ou nulo.
 * @param cidade     A cidade do endereço. Não pode ser vazio ou nulo.
 * @param uf         A unidade federativa (UF) do endereço. Não pode ser vazio ou nulo.
 * @param cep        O CEP do endereço. Não pode ser vazio ou nulo.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para solicitação de endereço")
public record EnderecoRequestDTO(

        @NotBlank(message = "O campo 'logradouro' é obrigatório")
        @Schema(description = "Logradouro do endereço", example = "Rua Professor Luiz")
        String logradouro,

        @NotBlank(message = "O campo 'numero' é obrigatório")
        @Schema(description = "Número do endereço", example = "11, apto 1")
        String numero,

        @NotBlank(message = "O campo 'bairro' é obrigatório")
        @Schema(description = "Bairro do endereço", example = "Centro")
        String bairro,

        @NotBlank(message = "O campo 'cidade' é obrigatório")
        @Schema(description = "Cidade do endereço", example = "São Paulo")
        String cidade,

        @NotBlank(message = "O campo 'uf' é obrigatório")
        @Schema(description = "Unidade Federativa do endereço", example = "SP")
        String uf,

        @NotBlank(message = "O campo 'cep' é obrigatório")
        @Schema(description = "CEP do endereço", example = "11222123")
        String cep
) {
    public Endereco toEntity() {
        return new Endereco(logradouro, numero, bairro, cidade, uf, cep);
    }
}