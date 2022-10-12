	package br.com.matheus.agendamentoconsultas.controller.form;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressAtualizationFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidEspecialization;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;
import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

public class AtualizacaoMedicoForm {

	private String nome;

	@ValidPhoneAtualizationFormat
	private String telefone;
	
	@ValidEspecialization
	private String especializacao;
	
	@ValidAddressAtualizationFormat
	private String endereco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		if (this.telefone != null)
			medico.setTelefone(this.telefone);
		if (this.especializacao != null)
			medico.setEspecializacao(Especializacao.stringToEnum(especializacao.toUpperCase()));
		if (this.endereco != null)
			medico.setEndereco(this.endereco);
		return medico;
	}
}
