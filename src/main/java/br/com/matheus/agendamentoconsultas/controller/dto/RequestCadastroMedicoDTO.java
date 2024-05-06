package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCadastroMedicoDTO(

	@NotNull(message = "O campo 'nome' é obrigatório")
	@NotBlank(message = "O campo 'nome' é obrigatório")
	String nome,

	@UniqueCrm
	@ValidCrmFormat
	@NotNull(message = "O campo 'crm' é obrigatório")
	@NotBlank(message = "O campo 'crm' é obrigatório")
	String crm,

	@Email
	@UniqueEmail
	@NotNull(message = "O campo 'email' é obrigatório")
	@NotBlank(message = "O campo 'email' é obrigatório")
	String email,

	@ValidTelefoneDTO
	TelefoneDTO telefone,

	@ValidEnderecoDTO
	EnderecoDTO endereco
	
//	@ValidEspecialization
//	@NotNull(message = "O campo 'especialização' é obrigatório")
//	private String especializacao;
) {

	public record TelefoneDTO(

			@NotNull(message = "O campo 'ddd' é obrigatório")
			@NotBlank(message = "O campo 'ddd' é obrigatório")
			String ddd,

			@NotNull(message = "O campo 'número' é obrigatório")
			@NotBlank(message = "O campo 'número' é obrigatório")
			String numero
	) {

		public Telefone toModel() {
			return new Telefone(ddd, numero);
		}
	}

	public record EnderecoDTO(

			@NotNull(message = "O campo 'logradouro' é obrigatório")
			@NotBlank(message = "O campo 'logradouro' é obrigatório")
			String logradouro,

			@NotNull(message = "O campo 'numero' é obrigatório")
			@NotBlank(message = "O campo 'numero' é obrigatório")
			String numero,

			@NotNull(message = "O campo 'bairro' é obrigatório")
			@NotBlank(message = "O campo 'bairro' é obrigatório")
			String bairro,

			@NotNull(message = "O campo 'cidade' é obrigatório")
			@NotBlank(message = "O campo 'cidade' é obrigatório")
			String cidade,

			@NotNull(message = "O campo 'uf' é obrigatório")
			@NotBlank(message = "O campo 'uf' é obrigatório")
			String uf,

			@NotNull(message = "O campo 'cep' é obrigatório")
			@NotBlank(message = "O campo 'cep' é obrigatório")
			String cep
	) {

		public Endereco toModel() {
			return new Endereco(logradouro, numero, bairro, cidade, uf, cep);
		}
	}

	public Medico toMedico() {
		return new Medico(nome, crm, email, telefone.toModel(), endereco.toModel());
	}
}