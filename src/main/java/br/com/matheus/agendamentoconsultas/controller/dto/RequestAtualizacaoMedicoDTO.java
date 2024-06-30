package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoRequestDTO;
import br.com.matheus.agendamentoconsultas.constraints.ValidEspecializacao;
import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

/**
 * DTO para solicitação de atualização de médico.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 *
 * @param nome O nome do médico.
 * @param especializacao A especialização do médico.
 * @param telefone O telefone do médico.
 * @param endereco O endereço do médico.
 */
@Schema(description = "DTO para atualização de médico")
public record RequestAtualizacaoMedicoDTO(

		@Schema(description = "Nome do médico", example = "Dr. João Silva")
    	String nome,

		@ValidEspecializacao
		@Schema(description = "Especialização do médico", example = "CARDIOLOGIA")
		String especializacao,

		@Valid
		@ValidTelefoneRequestDTO
		@Schema(description = "Telefone do médico")
		TelefoneRequestDTO telefone,

		@Valid
		@ValidEnderecoRequestDTO
		@Schema(description = "Endereço do médico")
		EnderecoRequestDTO endereco
) {}