package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidCrm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidCrmValidator implements ConstraintValidator<ValidCrm, String> {

	private static final Pattern CRM_PATTERN = Pattern.compile("^(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)(\\d{6})$");

	@Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return crm == null || crm.trim().isEmpty() || CRM_PATTERN.matcher(crm).matches();
	}
}