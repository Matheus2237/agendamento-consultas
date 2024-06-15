package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneRequestDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.TelefoneRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidTelefoneRequestDTOValidator implements ConstraintValidator<ValidTelefoneRequestDTO, TelefoneRequestDTO> {

	private static final Pattern DDD_PATTERN = Pattern.compile("^\\d{2}$");
	private static final Pattern NUMERO_PATTERN = Pattern.compile("^\\d{9}$");

	@Override
	public boolean isValid(TelefoneRequestDTO telefone, ConstraintValidatorContext context) {
		return isSomeDataNullOrEmpty(telefone) || isDDDOuNumeroValidos(telefone.ddd(), telefone.numero());
	}

	private boolean isSomeDataNullOrEmpty(TelefoneRequestDTO telefone) {
		return telefone == null || telefone.ddd() == null || telefone.ddd().trim().isEmpty()
				|| telefone.numero() == null || telefone.numero().trim().isEmpty();
	}

	private boolean isDDDOuNumeroValidos(String ddd, String numero) {
		return DDD_PATTERN.matcher(ddd).matches()
				&& NUMERO_PATTERN.matcher(numero).matches();
	}
}