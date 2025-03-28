package br.com.matheus.agendamentoconsultas.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * <p>
 * Representa o número de CPF de uma entidade, utilizado como um value object.
 * </p>
 * <p>
 * Esta classe é uma entidade incorporável que representa o número de CPF de uma entidade maior.
 * O número de CPF é obrigatório, único e não pode ser nulo.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CPF {

    @NotBlank
    @Column(name = "cpf", unique = true, nullable = false)
    private String value;
}