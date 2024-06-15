package br.com.matheus.agendamentoconsultas.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
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
public class CPF {

    @NotBlank
    @Column(name = "cpf", unique = true, nullable = false)
    private String value;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CPF cpf = (CPF) obj;
        return value.equals(cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}