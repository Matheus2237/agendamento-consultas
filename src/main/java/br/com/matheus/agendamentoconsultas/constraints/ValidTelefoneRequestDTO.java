package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidTelefoneRequestDTOValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * <p>
 * Anotação de validação para DTO de telefone válido.
 * </p>
 * <p>
 * Esta anotação é usada para validar se os dados no DTO de telefone estão corretos e completos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidTelefoneRequestDTOValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTelefoneRequestDTO {

	String message() default "Telefone inválido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}