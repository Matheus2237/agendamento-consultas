package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

/**
 * <p>
 * Representa o horário de atendimento de um médico no sistema de agendamento de consultas.
 * </p>
 * <p>
 * Armazena informações sobre o médico, horário inicial e horário final de atendimento.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medico_horario_atendimento")
public class HorarioAtendimento {

    @EmbeddedId
    private HorarioAtendimentoPK primaryKey;

    @MapsId("medicoId")
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @NotNull
    @Column(name = "hora_inicial")
    private LocalTime horaInicial;

    @NotNull
    @Column(name = "hora_final")
    private LocalTime horaFinal;
}