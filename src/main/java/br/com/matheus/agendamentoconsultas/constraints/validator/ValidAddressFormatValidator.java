package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidAddressFormat;

@Component
public class ValidAddressFormatValidator implements ConstraintValidator<ValidAddressFormat, String> {

	private final String regex = "^(?<logradouro>RUA|Rua|R\\.|AVENIDA|Avenida|AV\\.|Av\\.|TRAVESSA|Travessa|TRAV\\.|Trav\\.) (?<rua>[a-zA-Z\\száàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+), (?<numero>\\d+)(?<complemento>, [a-zA-z0-9 ]+)?, (?<bairro>[a-zA-Z\\s ]+), (?<cidade>[a-zA-Z\\száàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+), (?<uf>AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO), (?<cep>[0-9]{5}([-])?[0-9]{3})$";
	
	@Override
	public boolean isValid(String endereco, ConstraintValidatorContext context) {
		return Pattern.compile(regex).matcher(endereco).matches();
	}
}
