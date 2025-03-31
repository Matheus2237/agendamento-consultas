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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * <p>
 * Serviço para operações relacionadas a médicos.
 * </p>
 * <p>
 * Este serviço gerencia operações de CRUD e outras operações relacionadas aos médicos,
 * como cadastro, atualização, exclusão, e consulta de médicos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository, HorarioAtendimentoRepository horarioAtendimentoRepository) {
        this.medicoRepository = medicoRepository;
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    /**
     * Retorna uma paginação de todos os médicos cadastrados.
     *
     * @param pageable As informações da página solicitada.
     * @return Uma paginação paginada de médicos.
     */
    public Page<ResponseTodosMedicosDTO> visualizarTodos(final Pageable pageable) {
        Page<Medico> medicos = this.medicoRepository.findAll(pageable);
        return medicos.map(ResponseTodosMedicosDTO::new);
    }

    /**
     * Cadastra um novo médico com base nos dados fornecidos.
     *
     * @param medicoDTO Os dados do médico a ser cadastrado.
     * @return O médico cadastrado.
     */
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
                .map(hrAt -> new HorarioAtendimento(medico, DiaDaSemana.valueOf(hrAt.diaDaSemana()),
                        LocalTime.parse(hrAt.horaInicial()), LocalTime.parse(hrAt.horaFinal())))
                .collect(Collectors.toSet());
        medico.setHorariosAtendimento(horariosAtendimento);
        return this.medicoRepository.save(medico);
    }

    /**
     * Detalha as informações de um médico específico com base no ID fornecido.
     *
     * @param id O ID do médico a ser detalhado.
     * @return As informações detalhadas do médico.
     * @throws MedicoNaoEncontradoException Se o médico com o ID especificado não for encontrado.
     */
    public ResponseMedicoDTO detalharMedico(final Long id) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        return new ResponseMedicoDTO(medico);
    }

    /**
     * Atualiza as informações de um médico existente com base no ID e nos dados fornecidos.
     *
     * @param id               O ID do médico a ser atualizado.
     * @param dadosAtualizacao Os novos dados a serem atualizados para o médico.
     * @return As informações atualizadas do médico.
     * @throws MedicoNaoEncontradoException Se o médico com o ID especificado não for encontrado.
     */
    @Transactional
    public ResponseMedicoDTO atualizar(final Long id, final RequestAtualizacaoMedicoDTO dadosAtualizacao) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        ofNullable(dadosAtualizacao.nome()).filter(n -> !n.isBlank()).ifPresent(medico::setNome);
        ofNullable(dadosAtualizacao.especializacao()).filter(e -> !e.isBlank()).ifPresent(e -> medico.setEspecializacao(Especializacao.valueOf(e)));
        ofNullable(dadosAtualizacao.telefone()).ifPresent(t -> medico.setTelefone(new Telefone(t.ddd(), t.numero())));
        ofNullable(dadosAtualizacao.endereco()).ifPresent(e -> medico.setEndereco(new Endereco(e.logradouro(),
                e.numero(), e.bairro(), e.cidade(), e.uf(), e.cep())));
        return new ResponseMedicoDTO(medico);
    }

    /**
     * Deleta um médico existente com base no ID fornecido.
     *
     * @param id O ID do médico a ser deletado.
     * @throws MedicoNaoEncontradoException Se o médico com o ID especificado não for encontrado.
     */
    @Transactional
    public void deletar(Long id) {
        Long idMedico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new).getId();
        this.medicoRepository.deleteById(idMedico);
    }

    /**
     * Atualiza os horários de atendimento de um médico existente com base no ID e nos novos horários fornecidos.
     *
     * @param id                            O ID do médico cujos horários de atendimento devem ser atualizados.
     * @param horariosAtendimentoRequestDTO Os novos horários de atendimento a serem atualizados.
     * @return Um conjunto de DTOs de resposta dos horários de atendimento atualizados.
     * @throws MedicoNaoEncontradoException Se o médico com o ID especificado não for encontrado.
     */
    @Transactional
    public Set<HorarioAtendimentoResponseDTO> atualizarHorariosAtendimento(Long id, Set<HorarioAtendimentoRequestDTO> horariosAtendimentoRequestDTO) {
        Medico medico = this.medicoRepository.findById(id)
                .orElseThrow(MedicoNaoEncontradoException::new);
        horarioAtendimentoRepository.deleteByMedicoId(id);
        Set<HorarioAtendimento> horariosAtendimento = horariosAtendimentoRequestDTO.stream()
                .map(hrAt -> HorarioAtendimento.builder()
                        .medico(medico)
                        .diaDaSemana(DiaDaSemana.valueOf(hrAt.diaDaSemana()))
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