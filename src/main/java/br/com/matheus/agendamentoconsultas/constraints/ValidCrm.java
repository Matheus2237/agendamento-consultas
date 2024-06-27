package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidCrmValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Anotação de validação de CRM.
 * </p>
 * <p>
 * Esta anotação é usada para validar se o valor do campo anotado é um CRM válido.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidCrmValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCrm {

	String message() default "Crm inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}