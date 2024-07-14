package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * Repositório para operações de persistência de {@link br.com.matheus.agendamentoconsultas.model.Consulta}.
 * </p>
 * <p>
 * Este repositório oferece métodos para consultar e manipular dados de consultas médicas,
 * incluindo verificações de existência por paciente, médico, data e horário.
 * </p>
 * <p>
 * Também oferece um método para verificar se um médico já possui mais de doze consultas agendadas em um dia específico.
 * </p>
 *
 * @since 1.0.0
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    /**
     * Verifica se existe uma consulta para um paciente em uma data específica.
     *
     * @param pacienteId O ID do paciente
     * @param data A data da consulta
     * @return {@code true} se existir uma consulta para o paciente na data especificada, caso contrário {@code false}
     */
    boolean existsByPacienteIdAndData(Long pacienteId, LocalDate data);

    /**
     * Verifica se existe uma consulta para um médico em uma data e horário específicos.
     *
     * @param medicoId O ID do médico
     * @param data A data da consulta
     * @param horario O horário da consulta
     * @return {@code true} se existir uma consulta para o médico na data e horário especificados, caso contrário {@code false}
     */
    boolean existsByMedicoIdAndDataAndHorario(Long medicoId, LocalDate data, LocalTime horario);

    /**
     * Verifica se existe mais de doze consultas agendadas para um médico em um dia específico.
     *
     * @param medicoId O ID do médico
     * @param data A data da consulta
     * @return {@code true} se existir mais de doze consultas agendadas para o médico na data especificada, caso contrário {@code false}
     */
    default boolean existsMoreThanTwelveConsultationsForAnSpecifiedDayAndDoctor(Long medicoId, LocalDate data) {
        Long resultQuery = existsMoreThanTwelveConsultationsForAnSpecifiedDayAndDoctor(medicoId, data.toString());
        return resultQuery.equals(1L);
    }

    /**
     * Verifica se existe mais de doze consultas agendadas para um médico em um dia específico.
     *
     * @param medicoId O ID do médico
     * @param data A data da consulta
     * @return O número de consultas agendadas para o médico na data especificada
     */
    @Query(
            nativeQuery = true,
            value = """
                    SELECT COUNT(*) >= 12
                    FROM consulta c
                    WHERE c.id_medico = :medicoId
                        AND c.data = :data
                    """
    )
    Long existsMoreThanTwelveConsultationsForAnSpecifiedDayAndDoctor(@Param("medicoId") Long medicoId,
                                                                        @Param("data") String data);
}
