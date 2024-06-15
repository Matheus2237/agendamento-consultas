package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoRequestDTO;
import br.com.matheus.agendamentoconsultas.constraints.ValidEspecializacao;
import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneRequestDTO;
import jakarta.validation.Valid;

public record RequestAtualizacaoMedicoDTO(

    	String nome,

		@ValidEspecializacao
		String especializacao,

		@Valid
		@ValidTelefoneRequestDTO
		TelefoneRequestDTO telefone,

		@Valid
		@ValidEnderecoRequestDTO
	    EnderecoRequestDTO endereco
) {}