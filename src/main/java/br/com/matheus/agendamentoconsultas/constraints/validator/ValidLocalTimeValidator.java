package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidLocalTime}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidLocalTime. Ela verifica
 * se o horário informado está no formato válido HH:mm.
 * </p>
 * <p>
 * O formato aceito é de 00:00 até 23:59.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public class ValidLocalTimeValidator implements ConstraintValidator<ValidLocalTime, String> {

    private static final Pattern HH_MM_LOCAL_TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");

    /**
     * Verifica se o horário informado está no formato válido HH:mm.
     *
     * @param horario O horário que será validado.
     * @param context O contexto de validação.
     * @return {@code true} se o horário é válido ou é nulo/vazio, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(String horario, ConstraintValidatorContext context) {
        return horario == null || horario.trim().isEmpty() || HH_MM_LOCAL_TIME_PATTERN.matcher(horario).matches();
    }
}