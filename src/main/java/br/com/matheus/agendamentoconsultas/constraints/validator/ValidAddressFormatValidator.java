package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidAddressFormatValidator implements ConstraintValidator<ValidAddressFormat, String> {

	private final String regex = "^(RUA|Rua|R\\.|AVENIDA|Avenida|AV\\.|Av\\.|TRAVESSA|Travessa|TRAV\\.|Trav\\.) ([a-zA-Z\\száàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+), (\\d+)(, [a-zA-z0-9 ,]+)?, ([a-zA-Z\\száàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+), ([a-zA-Z\\száàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+), (AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO), ([0-9]{5}([-])?[0-9]{3})$";
	
	@Override
	public boolean isValid(String endereco, ConstraintValidatorContext context) {
		return Pattern.compile(regex).matcher(endereco).matches();
	}
}
