package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalTime horario;
}