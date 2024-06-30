package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidEspecializacaoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEspecializacao {

    String message() default "Especialização não encontrada.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}