package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * Representa uma consulta médica no sistema de agendamento de consultas.
 * </p>
 * <p>
 * Armazena informações como o médico, paciente, data e horário da consulta.
 * Cada consulta é associada a um médico e um paciente, e possui uma data e horário específicos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "consulta")
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

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDate data, LocalTime horario) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.horario = horario;
    }

    public Consulta(Medico medico, Paciente paciente, LocalDate data, LocalTime horario) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.horario = horario;
    }
}