package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de paciente.
 *
 * @param id       O ID do paciente.
 * @param nome     O nome do paciente.
 * @param cpf      O CPF do paciente.
 * @param email    O email do paciente.
 * @param telefone O telefone do paciente.
 * @param endereco O endereço do paciente.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para resposta de paciente")
public record ResponsePacienteDTO(

        @Schema(description = "ID do paciente", example = "1")
        Long id,

        @Schema(description = "Nome do paciente", example = "Maria Souza")
        String nome,

        @Schema(description = "CPF do paciente", example = "12345678900")
        String cpf,

        @Schema(description = "Email do paciente", example = "maria.souza@exemplo.com")
        String email,

        @Schema(description = "Telefone do paciente")
        TelefoneResponseDTO telefone,

        @Schema(description = "Endereço do paciente")
        EnderecoResponseDTO endereco
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de paciente.
     *
     * @param paciente A entidade de paciente.
     */
    public ResponsePacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf().getValue(), paciente.getEmail().getValue(),
                new TelefoneResponseDTO(paciente.getTelefone()), new EnderecoResponseDTO(paciente.getEndereco()));
    }
}