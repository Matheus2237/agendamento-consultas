package br.com.matheus.agendamentoconsultas.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public class PacienteDto {

	private Long id;
	private String nome;
	private String email;
	private Long telefone;
	private String cpf;
	private String endereco;
	
	public PacienteDto(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.email = paciente.getEmail();
		this.telefone = paciente.getTelefone();
		this.cpf = paciente.getCpf();
		this.endereco = paciente.getEndereco();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public Long getTelefone() {
		return telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	
	public static List<PacienteDto> converter(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDto::new).collect(Collectors.toList());
	}
}
