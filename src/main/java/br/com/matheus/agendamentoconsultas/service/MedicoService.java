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
import br.com.matheus.agendamentoconsultas.repository.HorarioAtendimentoRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository, HorarioAtendimentoRepository horarioAtendimentoRepository) {
        this.medicoRepository = medicoRepository;
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    public Page<ResponseTodosMedicosDTO> visualizarTodos(final Pageable pageable) {
        Page<Medico> medicos = this.medicoRepository.findAll(pageable);
        return medicos.map(ResponseTodosMedicosDTO::new);
    }

    @Transactional
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

    @Transactional
    public ResponseMedicoDTO atualizar(final Long id, final RequestAtualizacaoMedicoDTO dadosAtualizacao) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        ofNullable(dadosAtualizacao.nome()).filter(n -> !n.isBlank()).ifPresent(medico::setNome);
        ofNullable(dadosAtualizacao.especializacao()).filter(e -> !e.isBlank()).ifPresent(e -> medico.setEspecializacao(Especializacao.valueOf(e)));
        ofNullable(dadosAtualizacao.telefone()).ifPresent(t -> medico.setTelefone(t.toModel()));
        ofNullable(dadosAtualizacao.endereco()).ifPresent(e -> medico.setEndereco(e.toModel()));
        return new ResponseMedicoDTO(medico);
    }

    @Transactional
    public void deletar(Long id) {
        Long idMedico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new).getId();
        this.medicoRepository.deleteById(idMedico);
    }

    @Transactional
    public Set<HorarioAtendimentoResponseDTO> atualizarHorariosAtendimento(Long id, @Valid Set<HorarioAtendimentoRequestDTO> horariosAtendimentoRequestDTO) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        horarioAtendimentoRepository.deleteByMedicoId(id);
        Set<HorarioAtendimento> horariosAtendimento = horariosAtendimentoRequestDTO.stream()
                .map(hrAt -> HorarioAtendimento.builder()
                        .primaryKey(new HorarioAtendimentoPK(id, DiaDaSemana.valueOf(hrAt.diaDaSemana())))
                        .medico(medico)
                        .horaInicial(LocalTime.parse(hrAt.horaInicial()))
                        .horaFinal(LocalTime.parse(hrAt.horaFinal()))
                        .build())
                .collect(Collectors.toSet());
        horarioAtendimentoRepository.saveAll(horariosAtendimento);
        return horariosAtendimento.stream()
                .map(HorarioAtendimentoResponseDTO::new)
                .collect(Collectors.toSet());
    }
}