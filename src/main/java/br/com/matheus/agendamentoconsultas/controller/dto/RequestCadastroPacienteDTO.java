package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para solicitação de cadastro de paciente.
 *
 * @param nome     O nome do paciente. Não pode ser vazio ou nulo.
 * @param cpf      O CPF do paciente. Não pode ser vazio ou nulo.
 * @param email    O email do paciente. Não pode ser vazio ou nulo.
 * @param telefone O telefone do paciente. Não pode ser nulo.
 * @param endereco O endereço do paciente. Não pode ser nulo.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para cadastro de paciente")
public record RequestCadastroPacienteDTO(

        @NotBlank(message = "O campo 'nome' é obrigatório")
        @Schema(description = "Nome do paciente", example = "Maria Souza")
        String nome,

        @NotBlank(message = "O campo 'cpf' é obrigatório")
        @UniqueCpf
        @ValidCpf
        @Schema(description = "CPF do paciente", example = "12345678900")
        String cpf,

        @NotBlank(message = "O campo 'email' é obrigatório")
        @Email
        @UniqueEmail(repository = PacienteRepository.class)
        @Schema(description = "Email do paciente", example = "maria.souza@exemplo.com")
        String email,

        @Valid
        @NotNull(message = "O campo 'telefone' é obrigatório")
        @ValidTelefoneRequestDTO
        @Schema(description = "Telefone do paciente")
        TelefoneRequestDTO telefone,

        @Valid
        @NotNull(message = "O campo 'endereco' é obrigatório")
        @ValidEnderecoRequestDTO
        @Schema(description = "Endereço do paciente")
        EnderecoRequestDTO endereco
) {
}