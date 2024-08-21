package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidStringDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação de data de requisição de consulta.
 * </p>
 * <p>
 * Esta anotação é usada para validar se a data da requisição de consulta é válida.
 * </p>
 *
 * @author  Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidStringDateValidator.class)
@Target({PARAMETER, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidStringDate {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}