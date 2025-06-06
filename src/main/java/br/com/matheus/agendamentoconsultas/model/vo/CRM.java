package br.com.matheus.agendamentoconsultas.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class CRM {

    @NotBlank
    @Column(name = "crm", unique = true, nullable = false)
    private String value;

    public CRM(String value) {
        this.value = value;
    }
}