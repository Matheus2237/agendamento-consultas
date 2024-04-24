package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidPhoneAtualizationFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidPhoneAtualizationFormatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneAtualizationFormat {
	String message() default "O campo 'telefone' não está no formato correto: (DD) 9XXXX-XXXX ou (DD) XXXX-XXXX";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
