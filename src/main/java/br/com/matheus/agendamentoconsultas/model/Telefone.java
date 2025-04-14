package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Getter
@NoArgsConstructor
public class Telefone {

    @Column(name = "telefone_ddd")
    private String ddd;

    @Column(name = "telefone_numero")
    private String numero;

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
}