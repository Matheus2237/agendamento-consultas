package br.com.matheus.agendamentoconsultas.service;

import br.com.matheus.agendamentoconsultas.controller.dto.*;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

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

    public Medico cadastrar(final RequestCadastroMedicoDTO medicoDTO) {
        TelefoneRequestDTO telefoneDTO = medicoDTO.telefone();
        EnderecoRequestDTO enderecoDTO = medicoDTO.endereco();
        Medico medico = Medico.builder()
                .nome(medicoDTO.nome())
                .crm(new CRM(medicoDTO.crm()))
                .email(new Email(medicoDTO.email()))
                .telefone(new Telefone(telefoneDTO.ddd(), telefoneDTO.numero()))
                .endereco(new Endereco(enderecoDTO.logradouro(), enderecoDTO.numero(), enderecoDTO.bairro(),
                        enderecoDTO.cidade(), enderecoDTO.uf(), enderecoDTO.cep()))
                .especializacao(Especializacao.valueOf(medicoDTO.especializacao()))
                .build();
        Set<HorarioAtendimento> horariosAtendimento = medicoDTO.horariosAtendimento().stream()
                .map(hrAt -> new HorarioAtendimento(new HorarioAtendimentoPK(medico.getId(),
                        DiaDaSemana.valueOf(hrAt.diaDaSemana())), medico,
                        LocalTime.parse(hrAt.horaInicial()), LocalTime.parse(hrAt.horaFinal())))
                .collect(Collectors.toSet());
        medico.setHorariosAtendimento(horariosAtendimento);
        return this.medicoRepository.save(medico);
    }

    public ResponseMedicoDTO detalharMedico(final Long id) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        return new ResponseMedicoDTO(medico);
    }

    public ResponseMedicoDTO atualizar(final Long id, final RequestAtualizacaoMedicoDTO dadosAtualizacao) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        ofNullable(dadosAtualizacao.nome()).filter(n -> !n.isBlank()).ifPresent(medico::setNome);
        ofNullable(dadosAtualizacao.especializacao()).filter(e -> !e.isBlank()).ifPresent(e -> medico.setEspecializacao(Especializacao.valueOf(e)));
        ofNullable(dadosAtualizacao.telefone()).ifPresent(t -> medico.setTelefone(t.toModel()));
        ofNullable(dadosAtualizacao.endereco()).ifPresent(e -> medico.setEndereco(e.toModel()));
        return new ResponseMedicoDTO(medico);
    }

    public void deletar(Long id) {
        Long idMedico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new).getId();
        this.medicoRepository.deleteById(idMedico);
    }
}