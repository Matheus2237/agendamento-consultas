package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCadastroPacienteDTO {

	@NotNull(message = "O campo 'nome' é obrigatório")
	@NotBlank(message = "O campo 'nome' é obrigatório")
	private String nome;
	
	@Email
	@UniqueEmail
	@Column(unique = true)
	@NotNull(message = "O campo 'email' é obrigatório")
	@NotBlank(message = "O campo 'email' é obrigatório") 
	private String email;
	
	@ValidPhoneFormat
	@NotNull(message = "O campo 'telefone' é obrigatório")
	private String telefone;
	
	@UniqueCpf
	@ValidCpfFormat
	@Column(unique = true)
	@NotNull(message = "O campo 'cpf' é obrigatório")
	private String cpf;
	
	@ValidAddressFormat
	@NotNull(message = "O campo 'endereço' é obrigatório")
	@NotBlank(message = "O campo 'endereço' é obrigatório")
	private String endereco;
	
	public Paciente toPaciente() {
		return new Paciente(nome, email, telefone, cpf, endereco);
	}
}
