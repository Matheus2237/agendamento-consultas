package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public class VisualizarTodosPacientesDto {

	private Long id;
	private String nome;
	private String telefone;
	private String endereco;

	public VisualizarTodosPacientesDto(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.telefone = paciente.getTelefone();
		this.endereco = paciente.getEndereco();
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
	public String getEndereco() {
		return endereco;
	}
}
