package br.com.matheus.agendamentoconsultas.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @NotBlank
    @Column(name = "email", nullable = false)
    private String value;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Email email = (Email) obj;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}