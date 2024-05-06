package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;

public record ResponseTodosMedicosDTO(

	Long id,
	String nome,
	TelefoneResponseDTO telefone
//	private String especializacao;
) {

	private record TelefoneResponseDTO(String ddd, String numero) {

		public TelefoneResponseDTO(Telefone telefone) {
			this(telefone.getDdd(), telefone.getNumero());
		}
	}

	public ResponseTodosMedicosDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), new TelefoneResponseDTO(medico.getTelefone()));
//		this.especializacao = "medico.getEspecializacao().getName()";
	}
}