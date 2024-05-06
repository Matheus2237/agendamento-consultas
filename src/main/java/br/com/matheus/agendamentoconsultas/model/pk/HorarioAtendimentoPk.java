package br.com.matheus.agendamentoconsultas.model.pk;

import br.com.matheus.agendamentoconsultas.enums.DiaDaSemana;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioAtendimentoPk {

    @Column(name = "id_medico")
    private Long idMedico;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    private DiaDaSemana diaDaSemana;
}