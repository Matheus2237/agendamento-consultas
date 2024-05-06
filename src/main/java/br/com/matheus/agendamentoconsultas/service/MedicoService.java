package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.RequestAtualizacaoMedicoDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroMedicoDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseMedicoDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.ResponseTodosMedicosDTO;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<ResponseTodosMedicosDTO> visualizarTodos(final Pageable pageable) {
        Page<Medico> medicos = this.medicoRepository.findAll(pageable);
        return medicos.map(ResponseTodosMedicosDTO::new);
    }

    public Medico cadastrar(final RequestCadastroMedicoDTO requestCadastroMedicoDTO) {
        Medico medico = requestCadastroMedicoDTO.toMedico();
        return this.medicoRepository.save(medico);
    }

    public ResponseMedicoDTO detalharMedico(final Long id) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        return new ResponseMedicoDTO(medico);
    }

    public ResponseMedicoDTO atualizar(final Long id, final RequestAtualizacaoMedicoDTO atualizacao) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        atualizacao.atualizar(medico);
        return new ResponseMedicoDTO(medico);
    }

    public void deletar(Long id) {
        Long idMedico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new).getId();
        this.medicoRepository.deleteById(idMedico);
    }
}