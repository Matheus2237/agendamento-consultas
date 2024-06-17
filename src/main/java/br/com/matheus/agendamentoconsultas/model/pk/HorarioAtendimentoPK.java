package br.com.matheus.agendamentoconsultas.model.pk;

import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioAtendimentoPK {

    private Long medicoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    private DiaDaSemana diaDaSemana;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HorarioAtendimentoPK pk = (HorarioAtendimentoPK) obj;
        return this.medicoId.equals(pk.medicoId)
                && this.diaDaSemana.equals(pk.diaDaSemana);
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
