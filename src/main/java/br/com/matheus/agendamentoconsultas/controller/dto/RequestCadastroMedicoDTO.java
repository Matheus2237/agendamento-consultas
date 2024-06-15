package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RequestCadastroMedicoDTO(

	@NotBlank(message = "O campo 'nome' é obrigatório")
	String nome,

	@NotBlank(message = "O campo 'crm' é obrigatório")
	@UniqueCrm
	@ValidCrm
	String crm,

	@NotBlank(message = "O campo 'email' é obrigatório")
	@Email
	@UniqueEmail(repository = MedicoRepository.class)
	String email,

	@Valid
	@NotNull(message = "O campo 'telefone' é obrigatório")
	@ValidTelefoneRequestDTO
	TelefoneRequestDTO telefone,

	@Valid
	@NotNull(message = "O campo 'endereco' é obrigatório")
	@ValidEnderecoRequestDTO
	EnderecoRequestDTO endereco,

	@ValidEspecializacao
	@NotBlank(message = "O campo 'especialização' é obrigatório")
	String especializacao,

	@Valid
	@NotNull(message = "O campo 'horariosAtendimento' é obrigatório")
	Set<HorarioAtendimentoRequestDTO> horariosAtendimento
) {}