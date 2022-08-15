package br.com.matheus.agendamentoconsultas.model;

public class Endereco {

	private String logradouro;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void seCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
	return logradouro + ", nº" + numero.toString() + ", " + bairro +
			", " + cidade + ", " + uf + ", " + cep;
	}
}
