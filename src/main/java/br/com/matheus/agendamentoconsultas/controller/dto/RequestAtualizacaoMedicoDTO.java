	package br.com.matheus.agendamentoconsultas.controller.dto;

	import br.com.matheus.agendamentoconsultas.constraints.ValidAddressAtualizationFormat;
	import br.com.matheus.agendamentoconsultas.constraints.ValidEspecialization;
	import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;
	import br.com.matheus.agendamentoconsultas.model.Especializacao;
	import br.com.matheus.agendamentoconsultas.model.Medico;
	import lombok.Getter;
	import lombok.Setter;

	import static java.util.Optional.ofNullable;

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

    public void atualizar(Medico medico) {
		ofNullable(this.nome).ifPresent(medico::setNome);
		ofNullable(this.telefone).ifPresent(medico::setTelefone);
		ofNullable(this.especializacao).ifPresent(e -> medico.setEspecializacao(Especializacao.stringToEnum(e)));
		ofNullable(this.endereco).ifPresent(medico::setEndereco);
	}
}