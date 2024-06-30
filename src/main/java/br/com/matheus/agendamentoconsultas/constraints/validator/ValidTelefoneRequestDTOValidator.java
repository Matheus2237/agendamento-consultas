package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneRequestDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.TelefoneRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneRequestDTO}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidTelefoneRequestDTO. Ela verifica
 * se os campos DDD e número de telefone estão preenchidos corretamente.
 * </p>
 * <p>
 * O DDD deve ser um número de dois dígitos e o número de telefone deve ser composto por nove dígitos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class ValidTelefoneRequestDTOValidator implements ConstraintValidator<ValidTelefoneRequestDTO, TelefoneRequestDTO> {

	private static final Pattern DDD_PATTERN = Pattern.compile("^\\d{2}$");
	private static final Pattern NUMERO_PATTERN = Pattern.compile("^\\d{9}$");

	/**
	 * Valida se os campos DDD e número de telefone estão preenchidos corretamente.
	 *
	 * @param telefone O DTO contendo as informações de telefone que serão validadas.
	 * @param context  O contexto de validação.
	 * @return {@code true} se os campos são válidos ou são nulos/vazios, {@code false} caso contrário.
	 */
	@Override
	public boolean isValid(TelefoneRequestDTO telefone, ConstraintValidatorContext context) {
		return isSomeDataNullOrEmpty(telefone) || isDDDOuNumeroValidos(telefone.ddd(), telefone.numero());
	}

	/**
	 * Verifica se o DTO de telefone está nulo ou possui campos obrigatórios vazios.
	 *
	 * @param telefone O DTO contendo as informações de telefone que serão verificadas.
	 * @return {@code true} se algum campo obrigatório é nulo ou vazio, {@code false} caso contrário.
	 */
	private boolean isSomeDataNullOrEmpty(TelefoneRequestDTO telefone) {
		return isNull(telefone)
				|| isBlank(telefone.ddd())
				|| isBlank(telefone.numero());
	}

	/**
	 * Verifica se o DDD e o número de telefone informados são válidos.
	 *
	 * @param ddd O DDD que será validado.
	 * @param numero O número de telefone que será validado.
	 * @return {@code true} se o DDD e número são válidos, {@code false} caso contrário.
	 */
	private boolean isDDDOuNumeroValidos(String ddd, String numero) {
		return DDD_PATTERN.matcher(ddd).matches()
				&& NUMERO_PATTERN.matcher(numero).matches();
	}
}