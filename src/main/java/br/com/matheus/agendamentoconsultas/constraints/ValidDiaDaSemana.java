package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidDiaDaSemanaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDiaDaSemana {

    String message() default "Dia da semana inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}