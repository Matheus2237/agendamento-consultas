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
 * Representa o número de CRM de um médico, utilizado como um value object.
 * </p>
 * <p>
 * Esta classe é uma entidade incorporável que representa o número de CRM de um médico.
 * O número de CRM é obrigatório, único e não pode ser nulo.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CRM {

    @NotBlank
    @Column(name = "crm", unique = true, nullable = false)
    private String value;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CRM crm = (CRM) obj;
        return Objects.equals(value, crm.value);
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