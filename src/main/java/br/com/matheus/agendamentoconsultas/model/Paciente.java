package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.vo.CPF;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
@AllArgsConstructor
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
}