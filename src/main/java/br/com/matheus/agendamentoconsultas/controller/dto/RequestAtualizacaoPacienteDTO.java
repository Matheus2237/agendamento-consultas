package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressAtualizationFormat;
import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAtualizacaoPacienteDTO {

	private String nome;

	@ValidPhoneAtualizationFormat
	private String telefone;
	
	@ValidAddressAtualizationFormat
	private String endereco;

	public Paciente atualizar(Long id, PacienteRepository pacienteRepository) {
		Paciente paciente = pacienteRepository.getReferenceById(id);
		if(this.nome != null)
			paciente.setNome(this.nome);
		if(this.telefone != null)
			paciente.setTelefone(this.telefone);
		if(this.endereco != null)
			paciente.setEndereco(this.endereco);
		return paciente;
	}
}
