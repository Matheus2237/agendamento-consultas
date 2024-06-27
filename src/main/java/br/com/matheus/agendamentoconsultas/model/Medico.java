package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @Embedded
    private CRM crm;

    @Embedded
    private Email email;

    @Embedded
    private Telefone telefone;

    @Embedded
    private Endereco endereco;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Especializacao especializacao;

    @OneToMany(
            mappedBy = "medico",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<HorarioAtendimento> horariosAtendimento;
}