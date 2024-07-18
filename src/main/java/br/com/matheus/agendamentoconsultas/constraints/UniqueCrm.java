package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.UniqueCrmValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação para CRM único.
 * </p>
 * <p>
 * Esta anotação é usada para validar se o valor do campo anotado é um CRM único no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = UniqueCrmValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UniqueCrm {

    String message() default "Crm já registrada.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}