package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;

public class VisualizarTodosMedicoDto {

	private Long id;
	private String nome;
	private String telefone;
	private String especializacao;

	public VisualizarTodosMedicoDto(Medico medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.telefone = medico.getTelefone();
		this.especializacao = medico.getEspecializacao().getName();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getEspecializacao() {
		return especializacao;
	}
}
