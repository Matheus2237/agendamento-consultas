package br.com.matheus.agendamentoconsultas.model.pk;

import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Objects;

/**
 * <p>
 * Representa a chave primária composta para a entidade {@link br.com.matheus.agendamentoconsultas.model.HorarioAtendimento}.
 * </p>
 * <p>
 * A chave primária é composta pelo identificador do médico e o dia da semana.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class HorarioAtendimentoPK {

    private Long medicoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    @Getter
    private DiaDaSemana diaDaSemana;
}