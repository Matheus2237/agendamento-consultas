package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCadastroPacienteDTO(

        @NotBlank(message = "O campo 'nome' é obrigatório")
        String nome,

        @NotBlank(message = "O campo 'cpf' é obrigatório")
        @UniqueCpf
        @ValidCpf
        String cpf,

        @NotBlank(message = "O campo 'email' é obrigatório")
        @Email
        @UniqueEmail(repository = PacienteRepository.class)
        String email,

        @Valid
        @NotNull(message = "O campo 'telefone' é obrigatório")
        @ValidTelefoneRequestDTO
        TelefoneRequestDTO telefone,

        @Valid
        @NotNull(message = "O campo 'endereco' é obrigatório")
        @ValidEnderecoRequestDTO
        EnderecoRequestDTO endereco
) {}