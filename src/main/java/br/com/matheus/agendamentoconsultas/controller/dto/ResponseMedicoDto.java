package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import lombok.Getter;

@Getter
public class ResponseMedicoDto {
	
	private final Long id;
	private final String nome;
	private final String email;
	private final String telefone;
	private final String especializacao;
	private final String crm;
	private final String endereco;
	
	public ResponseMedicoDto(Medico medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.email = medico.getEmail();
		this.telefone = medico.getTelefone();
		this.especializacao = medico.getEspecializacao().getName();
		this.crm = medico.getCrm();
		this.endereco = medico.getEndereco();
	}
}