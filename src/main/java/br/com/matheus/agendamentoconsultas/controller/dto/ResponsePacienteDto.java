package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePacienteDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private String endereco;
	
	public ResponsePacienteDto(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.email = paciente.getEmail();
		this.telefone = paciente.getTelefone();
		this.cpf = paciente.getCpf();
		this.endereco = paciente.getEndereco();
	}
}
