package br.com.matheus.agendamentoconsultas.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

public class AtualizacaoMedicoForm {

	@NotNull @NotBlank
	private String nome;
	@NotNull @NotBlank @Email
	private String email;
	@NotNull
	private Long telefone;
	@NotNull @NotBlank
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
		medico.setNome(this.nome);
		medico.setEmail(this.email);
		medico.setTelefone(this.telefone);
		medico.setEndereco(this.endereco);
		return medico;
	}
}
