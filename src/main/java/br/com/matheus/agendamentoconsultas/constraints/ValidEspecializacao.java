package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidEspecializacaoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação de especialização.
 * </p>
 * <p>
 * Esta anotação é usada para validar se a especialização especificada é válida no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidEspecializacaoValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidEspecializacao {

    String message() default "Especialização não encontrada.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}