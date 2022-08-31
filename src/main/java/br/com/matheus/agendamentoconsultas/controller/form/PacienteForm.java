package br.com.matheus.agendamentoconsultas.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;
import br.com.matheus.agendamentoconsultas.model.Paciente;

public class PacienteForm {

	@NotNull(message = "O campo 'nome' é obrigatório")
	@NotBlank(message = "O campo 'nome' é obrigatório")
	private String nome;
	
	@Email
	@UniqueEmail
	@Column(unique = true)
	@NotNull(message = "O campo 'email' é obrigatório")
	@NotBlank(message = "O campo 'email' é obrigatório") 
	private String email;
	
	@NotNull(message = "O campo 'telefone' é obrigatório")
	private Long telefone;
	
	@Column(unique = true)
	@NotNull(message = "O campo 'cpf' é obrigatório")
	private String cpf;
	
	@ValidAddressFormat
	@NotNull(message = "O campo 'endereço' é obrigatório")
	@NotBlank(message = "O campo 'endereço' é obrigatório")
	private String endereco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Paciente toPaciente() {
		return new Paciente(nome, email, telefone, cpf, endereco);
	}
}
