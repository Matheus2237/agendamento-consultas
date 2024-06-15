package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

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