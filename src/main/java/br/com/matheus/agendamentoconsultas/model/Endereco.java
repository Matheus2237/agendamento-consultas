package br.com.matheus.agendamentoconsultas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Representa o endereço de um paciente no sistema de agendamento de consultas.
 * </p>
 * <p>
 * Armazena informações como logradouro, número, bairro, cidade, UF (Unidade Federativa) e CEP (Código de Endereçamento Postal).
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Embeddable
@Builder
@Getter
@NoArgsConstructor
public class Endereco {

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_bairro")
    private String bairro;

    @Column(name = "endereco_cidade")
    private String cidade;

    @Column(name = "endereco_uf")
    private String uf;

    @Column(name = "endereco_cep")
    private String cep;

    public Endereco(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
}