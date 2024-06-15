package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;

public record ResponseTodosMedicosDTO(

		Long id,
		String nome,
		String crm,
		String especializacao,
		String email,
		TelefoneResponseDTO telefone
) {

	public ResponseTodosMedicosDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm().getValue(), medico.getEspecializacao().toString(),
				medico.getEmail().getValue(), new TelefoneResponseDTO(medico.getTelefone()));
	}
}