package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidEspecializationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidEspecializationValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEspecialization {

	String message() default "Especialização não encontrada";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
