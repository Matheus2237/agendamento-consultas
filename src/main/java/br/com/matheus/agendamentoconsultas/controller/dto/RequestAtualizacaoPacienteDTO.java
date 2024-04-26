package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressAtualizationFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import lombok.Getter;
import lombok.Setter;

import static java.util.Optional.ofNullable;

@Getter
@Setter
public class RequestAtualizacaoPacienteDTO {

	private String nome;

	@ValidPhoneAtualizationFormat
	private String telefone;
	
	@ValidAddressAtualizationFormat
	private String endereco;

	public void atualizar(Paciente paciente) {
		ofNullable(this.nome).ifPresent(paciente::setNome);
		ofNullable(this.telefone).ifPresent(paciente::setTelefone);
		ofNullable(this.endereco).ifPresent(paciente::setEndereco);
	}
}