package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidCrmFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidCrmValidator implements ConstraintValidator<ValidCrmFormat, String> {

	@Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		final String regex = "^(CRM\\/)(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)[ -]?([0-9]{6})$";
		return Pattern.compile(regex).matcher(crm).matches();
	}
}
