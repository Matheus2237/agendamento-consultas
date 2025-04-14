package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Telefone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para solicitação de telefone.
 *
 * @param ddd    O DDD do telefone.
 * @param numero O número do telefone.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para solicitação de telefone")
public record TelefoneRequestDTO(

        @NotBlank(message = "O campo 'ddd' é obrigatório")
        @Schema(description = "DDD do telefone", example = "11")
        String ddd,

        @NotBlank(message = "O campo 'número' é obrigatório")
        @Schema(description = "Número do telefone", example = "987654321")
        String numero
) {
    public Telefone toEntity() {
        return new Telefone(ddd, numero);
    }
}