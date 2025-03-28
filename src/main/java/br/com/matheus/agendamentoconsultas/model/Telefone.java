package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * <p>
 * Representa um número de telefone no sistema de agendamento de consultas.
 * </p>
 * <p>
 * Esta classe encapsula o DDD e o número de telefone.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {

    @Column(name = "telefone_ddd")
    private String ddd;

    @Column(name = "telefone_numero")
    private String numero;
}