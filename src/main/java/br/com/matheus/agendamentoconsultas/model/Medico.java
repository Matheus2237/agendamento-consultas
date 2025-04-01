package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
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

    public static MedicoBuilder builder() {
        return new MedicoBuilder();
    }

    public static class MedicoBuilder {

        private String nome;
        private CRM crm;
        private Email email;
        private Telefone telefone;
        private Endereco endereco;
        private Especializacao especializacao;

        public MedicoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public MedicoBuilder crm(CRM crm) {
            this.crm = crm;
            return this;
        }

        public MedicoBuilder email(Email email) {
            this.email = email;
            return this;
        }

        public MedicoBuilder telefone(Telefone telefone) {
            this.telefone = telefone;
            return this;
        }

        public MedicoBuilder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public MedicoBuilder especializacao(Especializacao especializacao) {
            this.especializacao = especializacao;
            return this;
        }

        public Medico build() {
            return new Medico(nome, crm, email, telefone, endereco, especializacao);
        }
    }
}