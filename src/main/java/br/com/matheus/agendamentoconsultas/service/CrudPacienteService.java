package br.com.matheus.agendamentoconsultas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.matheus.agendamentoconsultas.controller.dto.VisualizarTodosPacientesDto;
import br.com.matheus.agendamentoconsultas.controller.form.AtualizacaoPacienteForm;
import br.com.matheus.agendamentoconsultas.controller.form.PacienteForm;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;

@Service
public class CrudPacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Page<VisualizarTodosPacientesDto> converterLista(Page<Paciente> pacientes) {
		return pacientes.map(VisualizarTodosPacientesDto::new);
	}
	
	public Paciente formToPaciente(PacienteForm pacienteForm) {
		String nome = pacienteForm.getNome();
		String email = pacienteForm.getEmail();
		String telefone = pacienteForm.getTelefone();
		String cpf = pacienteForm.getCpf();
		String endereco = pacienteForm.getEndereco();
		return new Paciente(nome, email, telefone, cpf, endereco);
	}
	
	public Paciente atualizar(Long id, AtualizacaoPacienteForm atualizacaoPacienteForm) {
		Paciente paciente = this.pacienteRepository.getReferenceById(id);
		if(atualizacaoPacienteForm.getNome() != null)
			paciente.setNome(atualizacaoPacienteForm.getNome());
		if(atualizacaoPacienteForm.getTelefone() != null)
			paciente.setTelefone(atualizacaoPacienteForm.getTelefone());
		if(atualizacaoPacienteForm.getEndereco() != null)
			paciente.setEndereco(atualizacaoPacienteForm.getEndereco());
		return paciente;
	}
}
