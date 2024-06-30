package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoRequestDTO;
import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

/**
 * DTO para solicitação de atualização de paciente.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 *
 * @param nome O nome do paciente.
 * @param telefone O telefone do paciente.
 * @param endereco O endereço do paciente.
 */
@Schema(description = "DTO para atualização de paciente")
public record RequestAtualizacaoPacienteDTO(

		@Schema(description = "Nome do paciente", example = "Maria Souza")
		String nome,

		@Valid
		@ValidTelefoneRequestDTO
		@Schema(description = "Telefone do paciente")
		TelefoneRequestDTO telefone,

		@Valid
		@ValidEnderecoRequestDTO
		@Schema(description = "Endereço do paciente")
		EnderecoRequestDTO endereco
) {}