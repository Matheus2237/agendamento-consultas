package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidDiaDaSemanaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação de dia da semana.
 * </p>
 * <p>
 * Esta anotação é usada para validar se o valor do campo anotado é um dia da semana válido.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidDiaDaSemanaValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidDiaDaSemana {

    String message() default "Dia da semana inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}