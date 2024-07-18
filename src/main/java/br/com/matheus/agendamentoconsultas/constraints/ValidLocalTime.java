package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidLocalTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação de horário local.
 * </p>
 * <p>
 * Esta anotação é usada para validar se o horário especificado está no formato válido (HH:mm).
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidLocalTimeValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidLocalTime {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}