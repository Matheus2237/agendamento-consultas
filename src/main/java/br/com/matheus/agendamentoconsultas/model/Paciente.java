package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.vo.CPF;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Representa um paciente no sistema de agendamento de consultas.
 * </p>
 * <p>
 * Esta classe contém informações básicas sobre o paciente, incluindo seu nome,
 * CPF, email, telefone e endereço.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    private String nome;

    @Embedded
    private CPF cpf;

    @Embedded
    private Email email;

    @Setter
    @Embedded
    private Telefone telefone;

    @Setter
    @Embedded
    private Endereco endereco;

    public Paciente(String nome, CPF cpf, Email email, Telefone telefone, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public static PacienteBuilder builder() {
        return new PacienteBuilder();
    }

    public static class PacienteBuilder {

        private String nome;
        private CPF cpf;
        private Email email;
        private Telefone telefone;
        private Endereco endereco;

        public PacienteBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public PacienteBuilder cpf(CPF cpf) {
            this.cpf = cpf;
            return this;
        }

        public PacienteBuilder email(Email email) {
            this.email = email;
            return this;
        }

        public PacienteBuilder telefone(Telefone telefone) {
            this.telefone = telefone;
            return this;
        }

        public PacienteBuilder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public Paciente build() {
            return new Paciente(nome, cpf, email, telefone, endereco);
        }
    }
}