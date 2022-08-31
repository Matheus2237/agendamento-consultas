package br.com.matheus.agendamentoconsultas.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;

public class AtualizacaoPacienteForm {

	private String nome;
	
	@Email
	@UniqueEmail
	@Column(unique = true)
	private String email;

	private Long telefone;
	
	@ValidAddressFormat
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Paciente atualizar(Long id, PacienteRepository pacienteRepository) {
		Paciente paciente = pacienteRepository.getReferenceById(id);
		if (this.nome != null) 
			paciente.setNome(this.nome);
		if (this.email != null)
			paciente.setEmail(this.email);
		if (this.telefone != null)
			paciente.setTelefone(this.telefone);
		if (this.endereco != null)
			paciente.setEndereco(this.endereco);
		return paciente;
	}
}
