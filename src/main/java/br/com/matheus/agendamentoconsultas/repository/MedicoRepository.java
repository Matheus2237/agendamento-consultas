package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * <p>
 * Repositório para operações de persistência de {@link br.com.matheus.agendamentoconsultas.model.Medico}.
 * </p>
 * <p>
 * Este repositório oferece métodos para consultar e manipular dados de médicos,
 * incluindo verificação de existência por CRM e e-mail.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    /**
     * Verifica se existe um médico com o CRM especificado.
     *
     * @param crm O CRM do médico
     * @return {@code true} se existir um médico com o CRM especificado, caso contrário {@code false}
     */
    boolean existsByCrmValue(String crm);

    /**
     * Verifica se existe um médico com o e-mail especificado.
     *
     * @param email O e-mail do médico
     * @return {@code true} se existir um médico com o e-mail especificado, caso contrário {@code false}
     */
    boolean existsByEmailValue(String email);

    /**
     * Encontra um médico disponível de forma aleatória para uma data e horário especificados.
     *
     * @param data    A data da consulta
     * @param horario O horário da consulta
     * @return Um {@link Optional} contendo um médico disponível, se encontrado
     */
    default Optional<Medico> findRandomAvailableMedicoToTheSpecifiedDate(LocalDate data, LocalTime horario) {
        String diaDaSemana = DiaDaSemana.getDiaDaSemanaPelaData(data).toString();
        return findRandomAvailableMedicoToTheSpecifiedDate(data, horario, diaDaSemana);
    }

    /**
     * Encontra um médico disponível de forma aleatória para uma data e horário
     * especificados, considerando o dia da semana.
     *
     * @param data        A data da consulta
     * @param horario     O horário da consulta
     * @param diaDaSemana O dia da semana da consulta
     * @return Um {@link Optional} contendo um médico disponível, se encontrado
     */
    @Query(
            nativeQuery = true,
            value = """
                    SELECT m.*
                    FROM medico m, medico_horario_atendimento mha
                    WHERE m.id = mha.id_medico
                        AND mha.dia_da_semana = :diaDaSemana
                        AND :horario BETWEEN mha.hora_inicial AND mha.hora_final
                        AND (SELECT COUNT(*)
                            FROM consulta c
                            WHERE c.id_medico = m.id
                            AND c.`data` = :data) < 12
                        AND m.id NOT IN (SELECT c.id_medico
                            FROM consulta c
                            WHERE c.`data` = :data
                                AND c.horario = CAST(:horario AS TIME))
                    ORDER BY RAND()
                    LIMIT 1
                    """)
    Optional<Medico> findRandomAvailableMedicoToTheSpecifiedDate(@Param("data") LocalDate data,
                                                                 @Param("horario") LocalTime horario,
                                                                 @Param("diaDaSemana") String diaDaSemana);
}