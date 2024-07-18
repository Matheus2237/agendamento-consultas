package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.UniqueCpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação para CPF único.
 * </p>
 * <p>
 * Esta anotação é usada para validar se o valor do campo anotado é um CPF único no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = UniqueCpfValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UniqueCpf {

    String message() default "Cpf já registrado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}