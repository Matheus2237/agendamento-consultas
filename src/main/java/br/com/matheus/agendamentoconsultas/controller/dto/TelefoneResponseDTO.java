package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Telefone;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de telefone.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 *
 * @param ddd O DDD do telefone.
 * @param numero O número do telefone.
 */
@Schema(description = "DTO para resposta de telefone")
public record TelefoneResponseDTO(

        @Schema(description = "DDD do telefone", example = "11")
        String ddd,

        @Schema(description = "Número do telefone", example = "987654321")
        String numero
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de telefone.
     *
     * @param telefone A entidade de telefone.
     */
    public TelefoneResponseDTO(Telefone telefone) {
        this(telefone.getDdd(), telefone.getNumero());
    }
}