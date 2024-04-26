	package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressAtualizationFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidEspecialization;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;
import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAtualizacaoMedicoDTO {

	private String nome;

	@ValidPhoneAtualizationFormat
	private String telefone;
	
	@ValidEspecialization
	private String especializacao;
	
	@ValidAddressAtualizationFormat
	private String endereco;
}