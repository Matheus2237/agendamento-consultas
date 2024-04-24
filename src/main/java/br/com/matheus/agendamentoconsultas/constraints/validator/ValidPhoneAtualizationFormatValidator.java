package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidPhoneAtualizationFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidPhoneAtualizationFormatValidator implements ConstraintValidator<ValidPhoneAtualizationFormat, String> {

	private final String regex = "^(\\(?[0-9]{2}\\)?) ?(9?[0-9]{4}-?[0-9]{4})$";
	
	@Override
	public boolean isValid(String telefone, ConstraintValidatorContext context) {
		if (telefone != null) {
			return Pattern.compile(regex).matcher(telefone).matches();
		}
		return true;
	}
}
