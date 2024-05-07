package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.enums.DiaDaSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoHorarioAtendimento {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    private DiaDaSemana diaDaSemana;

    @NotNull
    @Column(name = "hora_inicial")
    private LocalTime horaInicial;

    @NotNull
    @Column(name = "hora_final")
    private LocalTime horaFinal;
}