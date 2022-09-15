package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public class PacienteDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
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
	public String getTelefone() {
		return telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEndereco() {
		return endereco;
	}
}
