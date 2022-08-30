package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidCrmFormatValidator;

@Constraint(validatedBy = ValidCrmFormatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCrmFormat {
	String message() default "O campo 'crm' não está no formato correto: CRM/UF 123456";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
