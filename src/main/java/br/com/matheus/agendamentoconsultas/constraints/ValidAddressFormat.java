package br.com.matheus.agendamentoconsultas.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidAddressFormatValidator;

@Constraint(validatedBy = ValidAddressFormatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAddressFormat {
	String message() default "O endereço não está no formato válido: R. Nome da Rua, nº, Complemento(opcional), Nome do Bairro, Cidade, UF, (CEP)00000-000";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
