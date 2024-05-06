package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import lombok.Getter;

public record ResponseMedicoDTO(

	Long id,
	String nome,
	String crm,
	String email,
	TelefoneResponseDTO telefone,
	EnderecoResponseDTO endereco
//	private final String especializacao;

) {

	private record TelefoneResponseDTO(String ddd, String numero) {

		public TelefoneResponseDTO(Telefone telefone) {
			this(telefone.getDdd(), telefone.getNumero());
		}
	}

	private record EnderecoResponseDTO(
			String logradouro, String numero, String bairro, String cidade, String uf, String cep) {

		public EnderecoResponseDTO(Endereco endereco) {
			this(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(),
					endereco.getCidade(), endereco.getUf(), endereco.getCep());
		}
	}

	public ResponseMedicoDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm().getValue(), medico.getEmail().getValue(),
				new TelefoneResponseDTO(medico.getTelefone()), new EnderecoResponseDTO(medico.getEndereco()));
	}
}