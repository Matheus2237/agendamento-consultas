package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidDiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.Stream;

public class ValidDiaDaSemanaValidator implements ConstraintValidator<ValidDiaDaSemana, String> {

    @Override
    public boolean isValid(String diaDaSemana, ConstraintValidatorContext context) {
        return diaDaSemana == null || diaDaSemana.trim().isEmpty() || isDiaDaSemanaExistente(diaDaSemana);
    }

    private boolean isDiaDaSemanaExistente(String diaDaSemana) {
        return Stream.of(DiaDaSemana.values())
                .map(DiaDaSemana::toString)
                .anyMatch(esp -> esp.equals(diaDaSemana));
    }
}