package br.com.matheus.agendamentoconsultas.controller.dto;

import org.springframework.data.domain.Page;

import br.com.matheus.agendamentoconsultas.model.Medico;

public class VisualizarTodosMedicoDto {

	private Long id;
	private String nome;
	private String telefone;
	private String endereco;

	public VisualizarTodosMedicoDto(Medico medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.telefone = medico.getTelefone();
		this.endereco = medico.getEndereco();
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

	public static Page<VisualizarTodosMedicoDto> converter(Page<Medico> medicos) {
		return medicos.map(VisualizarTodosMedicoDto::new);
	}
}
