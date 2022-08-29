package br.com.matheus.agendamentoconsultas.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

public class AtualizacaoMedicoForm {

	private String nome;
	
	@Email
	@UniqueEmail
	@Column(unique = true)
	private String email;

	@Length(min = 10, max = 11, message = "O campo 'telefone' deve conter o ddd e o número")
	private Long telefone;
	
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
	
	public Medico atualizar(Long id, MedicoRepository medicoRepository) {
		Medico medico = medicoRepository.getReferenceById(id);
		if (this.nome != null) 
			medico.setNome(this.nome);
		if (this.email != null)
			medico.setEmail(this.email);
		if (this.telefone != null)
			medico.setTelefone(this.telefone);
		if (this.endereco != null)
			medico.setEndereco(this.endereco);
		return medico;
	}
}
