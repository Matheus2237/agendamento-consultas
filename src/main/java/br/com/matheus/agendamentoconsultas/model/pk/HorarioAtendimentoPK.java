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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class HorarioAtendimentoPK {

    private Long medicoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    private DiaDaSemana diaDaSemana;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        HorarioAtendimentoPK pk = (HorarioAtendimentoPK) obj;
        return Objects.equals(this.medicoId, pk.medicoId)
                && Objects.equals(this.diaDaSemana, pk.diaDaSemana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicoId, diaDaSemana);
    }

    @Override
    public String toString() {
        return "HorarioAtendimentoPK{" +
                "medicoId=" + medicoId +
                ", diaDaSemana=" + diaDaSemana +
                '}';
    }
}
