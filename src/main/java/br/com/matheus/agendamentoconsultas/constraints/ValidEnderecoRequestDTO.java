package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidEnderecoRequestDTOValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Anotação de validação de DTO de endereço.
 * </p>
 * <p>
 * Esta anotação é usada para validar se os campos obrigatórios do DTO de endereço estão preenchidos
 * corretamente.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidEnderecoRequestDTOValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidEnderecoRequestDTO {

    String message() default "Endereço inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}