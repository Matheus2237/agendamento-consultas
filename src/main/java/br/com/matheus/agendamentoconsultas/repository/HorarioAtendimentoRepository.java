package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 * Repositório para operações de persistência de {@link br.com.matheus.agendamentoconsultas.model.HorarioAtendimento}.
 * </p>
 * <p>
 * Este repositório oferece métodos para consultar e manipular dados de horários de atendimento
 * associados a médicos, incluindo exclusão por ID do médico.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Repository
public interface HorarioAtendimentoRepository extends JpaRepository<HorarioAtendimento, HorarioAtendimentoPK> {

    /**
     * Exclui todos os horários de atendimento de um médico pelo ID do médico.
     *
     * @param id O ID do médico cujos horários de atendimento serão excluídos
     */
    void deleteByMedicoId(Long id);

    /**
     * Encontra um horário de atendimento pelo ID do médico e dia da semana.
     *
     * @param medicoId O ID do médico
     * @param diaDaSemana O dia da semana
     * @return Um {@link Optional} contendo o horário de atendimento, se encontrado
     */
    Optional<HorarioAtendimento> findByPrimaryKeyMedicoIdAndPrimaryKeyDiaDaSemana(Long medicoId, DiaDaSemana diaDaSemana);
}