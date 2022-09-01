package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneFormat;

@Component
public class ValidPhoneFormatValidator implements ConstraintValidator<ValidPhoneFormat, String> {

	private final String regex = "^(\\(?[0-9]{2}\\)?) ?(9?[0-9]{4}-?[0-9]{4})$";
	
	@Override
	public boolean isValid(String telefone, ConstraintValidatorContext context) {
		return Pattern.compile(regex).matcher(telefone).matches();
	}
}
