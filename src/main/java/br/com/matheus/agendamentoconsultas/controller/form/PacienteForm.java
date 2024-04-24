package br.com.matheus.agendamentoconsultas.controller.form;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCpf;
import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidCpfFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneFormat;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
	
	@ValidPhoneFormat
	@NotNull(message = "O campo 'telefone' é obrigatório")
	private String telefone;
	
	@UniqueCpf
	@ValidCpfFormat
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
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
