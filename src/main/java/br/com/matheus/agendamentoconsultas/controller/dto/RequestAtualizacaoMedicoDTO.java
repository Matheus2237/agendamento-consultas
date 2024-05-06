package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoDTO;
import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneDTO;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import lombok.Getter;
import lombok.Setter;

import static java.util.Optional.ofNullable;

public record RequestAtualizacaoMedicoDTO(

    String nome,

    @ValidTelefoneDTO
    TelefoneDTO telefone,

//	@ValidEspecialization
//	private String especializacao;

    @ValidEnderecoDTO
    EnderecoDTO endereco
) {

	public record TelefoneDTO(String ddd, String numero) {

		public void atualizar(Telefone telefone) {
			ofNullable(this.ddd).filter(d -> !d.isBlank()).ifPresent(telefone::setDdd);
			ofNullable(this.numero).filter(n -> !n.isBlank()).ifPresent(telefone::setNumero);
		}
	}

	public record EnderecoDTO(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {

		public void atualizar(Endereco endereco) {
			ofNullable(this.logradouro).filter(l -> !l.isBlank()).ifPresent(endereco::setLogradouro);
			ofNullable(this.numero).filter(n -> !n.isBlank()).ifPresent(endereco::setNumero);
			ofNullable(this.bairro).filter(b -> !b.isBlank()).ifPresent(endereco::setBairro);
			ofNullable(this.cidade).filter(c -> !c.isBlank()).ifPresent(endereco::setCidade);
			ofNullable(this.uf).filter(u -> !u.isBlank()).ifPresent(endereco::setUf);
			ofNullable(this.cep).filter(c -> !c.isBlank()).ifPresent(endereco::setCep);
		}
	}

    public void atualizar(Medico medico) {
		ofNullable(this.nome).filter(n -> !n.isBlank()).ifPresent(medico::setNome);
		ofNullable(this.telefone).ifPresent(t -> t.atualizar(medico.getTelefone()));
//		ofNullable(this.especializacao).ifPresent(e -> medico.setEspecializacao(Especializacao.stringToEnum(e)));
		ofNullable(this.endereco).ifPresent(e -> e.atualizar(medico.getEndereco()));
    }
}