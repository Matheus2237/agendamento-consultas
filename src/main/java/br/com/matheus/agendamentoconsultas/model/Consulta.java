package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}