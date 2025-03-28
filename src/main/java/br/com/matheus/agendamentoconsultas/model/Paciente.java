package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.vo.CPF;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
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
@Builder
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

    public Paciente(Long id, String nome, CPF cpf, Email email, Telefone telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}