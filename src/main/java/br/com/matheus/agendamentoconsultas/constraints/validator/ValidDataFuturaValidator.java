package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidDataFutura;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidDataFuturaValidator implements ConstraintValidator<ValidDataFutura, String> {

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        return isDataDaConsultaAmanhaOuPosterior(data);
    }

    private boolean isDataDaConsultaAmanhaOuPosterior(String data) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataConsulta = LocalDate.parse(data);
        return dataConsulta.isAfter(hoje);
    }
}