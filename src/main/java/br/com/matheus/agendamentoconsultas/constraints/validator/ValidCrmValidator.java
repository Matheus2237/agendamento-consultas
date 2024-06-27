package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidCrm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidCrm}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidCrm. Ela verifica
 * se crm o está no formato válido.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class ValidCrmValidator implements ConstraintValidator<ValidCrm, String> {

	private static final Pattern CRM_PATTERN = Pattern.compile("^(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)(\\d{6})$");

	/**
	 * Verifica se o CRM informado está no formato válido.
	 *
	 * @param crm O CRM que será validado.
	 * @param context O contexto de validação.
	 * @return {@code true} se o CRM está no formato válido ou é nulo/vazio, {@code false} caso contrário.
	 */
	@Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return crm == null || crm.trim().isEmpty() || CRM_PATTERN.matcher(crm).matches();
	}
}