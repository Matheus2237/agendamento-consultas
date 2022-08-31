package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.matheus.agendamentoconsultas.constraints.validator.UniqueCpfValidator;

@Constraint(validatedBy = UniqueCpfValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {
	String message() default "Este cpf já está registrado.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
