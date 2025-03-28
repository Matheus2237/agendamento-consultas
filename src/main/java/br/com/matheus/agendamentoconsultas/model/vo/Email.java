package br.com.matheus.agendamentoconsultas.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Representa o endereço de e-mail, utilizado como um value object.
 * </p>
 * <p>
 * Esta classe é uma entidade incorporável que representa um endereço de e-mail.
 * O endereço de e-mail é obrigatório e não pode ser nulo.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Email {

    @NotBlank
    @Column(name = "email", nullable = false)
    private String value;

    public Email(String value) {
        this.value = value;
    }
}