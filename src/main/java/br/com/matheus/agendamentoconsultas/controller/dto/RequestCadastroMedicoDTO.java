package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCadastroMedicoDTO {

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
	
	@ValidEspecialization
	@NotNull(message = "O campo 'especialização' é obrigatório")
	private String especializacao;
	
	@UniqueCrm
	@ValidCrmFormat
	@Column(unique = true)
	@NotNull(message = "O campo 'crm' é obrigatório")
	private String crm;
	
	@ValidAddressFormat
	@NotNull(message = "O campo 'endereço' é obrigatório")
	@NotBlank(message = "O campo 'endereço' é obrigatório")
	private String endereco;

	public Medico toMedico() {
		Especializacao especializacaoEnum = Especializacao.stringToEnum(this.especializacao);
		return new Medico(nome, email, telefone, especializacaoEnum, crm, endereco);
	}
}
