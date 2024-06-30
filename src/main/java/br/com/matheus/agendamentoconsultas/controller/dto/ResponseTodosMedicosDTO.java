package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para listar todos os médicos.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 *
 * @param id O ID do médico.
 * @param nome O nome do médico.
 * @param crm O CRM do médico.
 * @param especializacao A especialização do médico.
 * @param email O email do médico.
 * @param telefone O telefone do médico.
 */
@Schema(description = "DTO para listar todos os médicos")
public record ResponseTodosMedicosDTO(

		@Schema(description = "ID do médico", example = "1")
		Long id,

		@Schema(description = "Nome do médico", example = "Dr. João Silva")
		String nome,

		@Schema(description = "CRM do médico", example = "MG123456")
		String crm,

		@Schema(description = "Especialização do médico", example = "CARDIOLOGIA")
		String especializacao,

		@Schema(description = "Email do médico", example = "joao.silva@exemplo.com")
		String email,

		@Schema(description = "Telefone do médico")
		TelefoneResponseDTO telefone
) {

	/**
	 * Construtor que cria um DTO de resposta a partir de uma entidade de médico.
	 *
	 * @param medico A entidade de médico.
	 */
	public ResponseTodosMedicosDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm().getValue(), medico.getEspecializacao().toString(),
				medico.getEmail().getValue(), new TelefoneResponseDTO(medico.getTelefone()));
	}
}