package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidCpfFormatValidator;

@Constraint(validatedBy = ValidCpfFormatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCpfFormat {
	String message() default "O campo 'cpf' não está no formato correto: 123.456.789-00";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
