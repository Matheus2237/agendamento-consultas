package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoRequestDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.EnderecoRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidEnderecoRequestDTOValidator implements ConstraintValidator<ValidEnderecoRequestDTO, EnderecoRequestDTO> {

	private static final Pattern ESTADO_PATTERN = Pattern.compile("^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$");
	private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");

	@Override
	public boolean isValid(EnderecoRequestDTO endereco, ConstraintValidatorContext context) {
		return isSomeDataNullOrEmpty(endereco) || isUfECepValidos(endereco.uf(), endereco.cep());
	}

	private boolean isSomeDataNullOrEmpty(EnderecoRequestDTO endereco) {
		return endereco == null
				|| endereco.logradouro() == null || endereco.logradouro().trim().isEmpty()
				|| endereco.numero() == null || endereco.numero().trim().isEmpty()
				|| endereco.bairro() == null || endereco.bairro().trim().isEmpty()
				|| endereco.cidade() == null || endereco.cidade().trim().isEmpty()
				|| endereco.uf() == null || endereco.uf().trim().isEmpty()
				|| endereco.cep() == null || endereco.cep().trim().isEmpty();
	}

	private boolean isUfECepValidos(String uf, String cep) {
		return ESTADO_PATTERN.matcher(uf).matches()
				&& CEP_PATTERN.matcher(cep).matches();
	}
}