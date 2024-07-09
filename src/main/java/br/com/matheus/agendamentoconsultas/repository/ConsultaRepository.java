package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndData(Long pacienteId, LocalDate data);

    boolean existsByMedicoIdAndDataAndHorario(Long medicoId, LocalDate data, LocalTime horario);

    default boolean existsMoreThanTwelveConsultationsForAnSpecifiedDayAndDoctor(Long medicoId, LocalDate data) {
        Long resultQuery = existsMoreThanTwelveConsultationsForAnSpecifiedDayAndDoctor(medicoId, data.toString());
        return resultQuery.equals(1L);
    }

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
