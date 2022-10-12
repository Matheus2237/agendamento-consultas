package br.com.matheus.agendamentoconsultas.controller.form;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressAtualizationFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;

public class AtualizacaoPacienteForm {

	private String nome;

	@ValidPhoneAtualizationFormat
	private String telefone;
	
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
