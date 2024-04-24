package br.com.matheus.agendamentoconsultas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime data;
	private LocalDateTime dataCriacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}
