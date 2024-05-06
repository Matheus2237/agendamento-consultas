package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class Telefone {

    @Column(name = "telefone_ddd")
    private String ddd;

    @Column(name = "telefone_numero")
    private String numero;
}