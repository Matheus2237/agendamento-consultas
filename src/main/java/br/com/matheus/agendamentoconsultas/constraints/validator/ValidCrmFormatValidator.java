package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidCrmFormat;

@Component
public class ValidCrmFormatValidator implements ConstraintValidator<ValidCrmFormat, String> {

	private final String regex = "^(CRM\\/)(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)[ -]?([0-9]{6})$";
	
	@Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return Pattern.compile(regex).matcher(crm).matches();
	}
}
