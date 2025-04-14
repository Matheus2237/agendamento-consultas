package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Representa um médico no sistema de agendamento de consultas.
 * </p>
 * <p>
 * Esta classe contém informações detalhadas sobre o médico, incluindo seu nome,
 * CRM, email, telefone, endereço, especialização e horários de atendimento.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    private String nome;

    @Embedded
    private CRM crm;

    @Embedded
    private Email email;

    @Setter
    @Embedded
    private Telefone telefone;

    @Setter
    @Embedded
    private Endereco endereco;

    @Setter
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Especializacao especializacao;

    @OneToMany(
            mappedBy = "medico",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<HorarioAtendimento> horariosAtendimento;

    public Medico(String nome, CRM crm, Email email, Telefone telefone,
                  Endereco endereco, Especializacao especializacao) {
        this.nome = nome;
        this.crm = crm;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.especializacao = especializacao;
        this.horariosAtendimento = new HashSet<>();
    }

    public void adicionaHorarioAtendimento(DiaDaSemana diaDaSemana, LocalTime horaInicial, LocalTime horaFinal) {
        HorarioAtendimento horarioAtendimento = new HorarioAtendimento(this,diaDaSemana,horaInicial,horaFinal);
        this.horariosAtendimento.add(horarioAtendimento);
    }
}