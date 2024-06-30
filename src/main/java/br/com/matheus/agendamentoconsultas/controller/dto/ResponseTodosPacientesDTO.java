package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para listar todos os pacientes.
 *
 * @param id       O ID do paciente.
 * @param nome     O nome do paciente.
 * @param email    O email do paciente.
 * @param telefone O telefone do paciente.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para listar todos os pacientes")
public record ResponseTodosPacientesDTO(

        @Schema(description = "ID do paciente", example = "1")
        Long id,

        @Schema(description = "Nome do paciente", example = "Maria Souza")
        String nome,

        @Schema(description = "Email do paciente", example = "maria.souza@exemplo.com")
        String email,

        @Schema(description = "Telefone do paciente")
        TelefoneResponseDTO telefone
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de paciente.
     *
     * @param paciente A entidade de paciente.
     */
    public ResponseTodosPacientesDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail().getValue(),
                new TelefoneResponseDTO(paciente.getTelefone()));
    }
}