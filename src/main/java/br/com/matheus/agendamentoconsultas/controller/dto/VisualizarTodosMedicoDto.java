package br.com.matheus.agendamentoconsultas.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.matheus.agendamentoconsultas.model.Medico;

public class VisualizarTodosMedicoDto {

	private Long id;
	private String nome;
	private Long telefone;
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
	public Long getTelefone() {
		return telefone;
	}
	public String getEndereco() {
		return endereco;
	}

	public static List<VisualizarTodosMedicoDto> converter(List<Medico> medicos) {
		return medicos.stream().map(VisualizarTodosMedicoDto::new).collect(Collectors.toList());
	}
}
