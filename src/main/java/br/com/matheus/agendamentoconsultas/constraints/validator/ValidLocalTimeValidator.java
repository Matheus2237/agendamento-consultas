package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidLocalTimeValidator implements ConstraintValidator<ValidLocalTime, String> {

    private static final Pattern HH_MM_LOCAL_TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");

    @Override
    public boolean isValid(String horario, ConstraintValidatorContext context) {
        return horario == null || horario.trim().isEmpty() || HH_MM_LOCAL_TIME_PATTERN.matcher(horario).matches();
    }
}
