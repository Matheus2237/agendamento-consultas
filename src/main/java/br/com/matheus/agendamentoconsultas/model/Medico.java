package br.com.matheus.agendamentoconsultas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	@Enumerated(value = EnumType.STRING)
	private Especializacao especializacao;
	private String crm;
	private String endereco;
	
	@OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
	private List<Consulta> consultas;
	
	public Medico() {
	}

	public Medico(String nome, String email, String telefone, Especializacao especializacao, String crm, String endereco) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.especializacao =  especializacao;
		this.crm = crm;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Especializacao getEspecializacao() {
		return especializacao;
	}
	public void setEspecializacao(Especializacao especializacao) {
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
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
