package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public record ResponseMedicoDTO(

	Long id,
	String nome,
	String crm,
	String email,
	TelefoneResponseDTO telefone,
	EnderecoResponseDTO endereco,
	String especializacao,
	Set<HorarioAtendimentoResponseDTO> horariosAtendimento
) {

	public ResponseMedicoDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm().getValue(), medico.getEmail().getValue(),
				new TelefoneResponseDTO(medico.getTelefone()), new EnderecoResponseDTO(medico.getEndereco()),
				medico.getEspecializacao().toString(), medico.getHorariosAtendimento().stream()
						.map(HorarioAtendimentoResponseDTO::new).collect(Collectors.toSet()));
	}
}