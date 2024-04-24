package br.com.matheus.agendamentoconsultas.controller.form;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCrm;
import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidCrmFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidEspecialization;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneFormat;
import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
	
	@ValidPhoneFormat
	@NotNull(message = "O campo 'telefone' é obrigatório")
	private String telefone;
	
	@ValidEspecialization
	@NotNull(message = "O campo 'especialização' é obrigatório")
	private String especializacao;
	
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEspecializacao() {
		return especializacao;
	}
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
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
		Especializacao especializacaoEnum = Especializacao.stringToEnum(this.especializacao);
		return new Medico(nome, email, telefone, especializacaoEnum, crm, endereco);
	}
}
