package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidDataFuturaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação para garantir que uma data é futura.
 * </p>
 * <p>
 * Esta anotação é usada para validar se a data anotada é uma data futura.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidDataFuturaValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidDataFutura {

    String message() default "Data da solicitação já passada.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}