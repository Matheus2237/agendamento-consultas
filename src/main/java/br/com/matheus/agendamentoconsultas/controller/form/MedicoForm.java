package br.com.matheus.agendamentoconsultas.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCrm;
import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidCrmFormat;
import br.com.matheus.agendamentoconsultas.model.Medico;

public class MedicoForm {

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
	@Length(min = 10, max = 11, message = "O campo 'telefone' deve conter o ddd e o número")
	private Long telefone;
	
	@UniqueCrm
	@ValidCrmFormat
	@Column(unique = true)
	@NotNull(message = "O campo 'crm' é obrigatório")
	private String crm;
	
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
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Medico toMedico() {
		return new Medico(nome, email, telefone, crm, endereco);
	}
}
