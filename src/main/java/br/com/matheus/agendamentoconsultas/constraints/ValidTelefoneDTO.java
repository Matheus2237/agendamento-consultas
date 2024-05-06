package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidTelefoneDTOValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidTelefoneDTOValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTelefoneDTO {

	String message() default "Telefone inválido.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}