package br.com.matheus.agendamentoconsultas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.agendamentoconsultas.controller.form.AtualizacaoMedicoForm;
import br.com.matheus.agendamentoconsultas.controller.form.MedicoForm;
import br.com.matheus.agendamentoconsultas.model.Especializacao;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

@Service
public class CrudMedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;

	public Medico formToMedico(MedicoForm medicoForm) {
		String nome =  medicoForm.getNome();
		String email = medicoForm.getEmail();
		String telefone = medicoForm.getTelefone();
		Especializacao especializacao = Especializacao.stringToEnum(medicoForm.getEspecializacao().toUpperCase());
		String crm = medicoForm.getCrm();
		String endereco = medicoForm.getEndereco();
		return new Medico(nome, email, telefone, especializacao, crm, endereco);
	}
	
	public Medico atualizar(Long id, AtualizacaoMedicoForm atualizacaoMedicoForm) {
		Medico medico = this.medicoRepository.getReferenceById(id);
		if(atualizacaoMedicoForm.getNome() != null)
			medico.setNome(atualizacaoMedicoForm.getNome());
		if (atualizacaoMedicoForm.getTelefone() != null)
			medico.setTelefone(atualizacaoMedicoForm.getTelefone());
		if (atualizacaoMedicoForm.getEspecializacao() != null)
			medico.setEspecializacao(Especializacao.stringToEnum(atualizacaoMedicoForm.getEspecializacao().toUpperCase()));
		if (atualizacaoMedicoForm.getEndereco() != null)
			medico.setEndereco(atualizacaoMedicoForm.getEndereco());
		return medico;
	}
}
