package br.com.matheus.agendamentoconsultas.model.pk;

import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.requireNonNull;

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
@NoArgsConstructor
public final class HorarioAtendimentoPK {

    private Long medicoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    @Getter
    private DiaDaSemana diaDaSemana;

    public HorarioAtendimentoPK(Medico medico, DiaDaSemana diaDaSemana) {
        this.medicoId = requireNonNull(medico).getId();
        this.diaDaSemana = diaDaSemana;
    }
}