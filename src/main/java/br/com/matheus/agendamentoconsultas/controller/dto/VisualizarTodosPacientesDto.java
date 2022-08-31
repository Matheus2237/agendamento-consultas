package br.com.matheus.agendamentoconsultas.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public class VisualizarTodosPacientesDto {

	private Long id;
	private String nome;
	private Long telefone;
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
	public Long getTelefone() {
		return telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	
	public static List<VisualizarTodosPacientesDto> converter(List<Paciente> pacientes) {
		return pacientes.stream().map(VisualizarTodosPacientesDto::new).collect(Collectors.toList());
	}
}
