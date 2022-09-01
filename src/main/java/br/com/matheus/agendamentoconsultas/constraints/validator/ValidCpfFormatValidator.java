package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidCpfFormat;

@Component
public class ValidCpfFormatValidator implements ConstraintValidator<ValidCpfFormat, String> {

	private final String regex = "^([0-9]{3})\\.?([0-9]{3})\\.?([0-9]{3})\\-?([0-9]{2})$";
	
	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		return Pattern.compile(regex).matcher(cpf).matches();
	}
}
