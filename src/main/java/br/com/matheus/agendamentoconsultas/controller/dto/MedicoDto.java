package br.com.matheus.agendamentoconsultas.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.matheus.agendamentoconsultas.model.Medico;

public class MedicoDto {
	
	private Long id;
	private String nome;
	private String email;
	private Long telefone;
	private Long crm;
	private String endereco;
	
	public MedicoDto(Medico medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.email = medico.getEmail();
		this.telefone = medico.getTelefone();
		this.crm = medico.getCrm();
		this.endereco = medico.getEndereco();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Long getTelefone() {
		return telefone;
	}

	public Long getCrm() {
		return crm;
	}

	public String getEndereco() {
		return endereco;
	}

	public static List<MedicoDto> converter(List<Medico> medicos) {
		return medicos.stream().map(MedicoDto::new).collect(Collectors.toList());
	}
}
