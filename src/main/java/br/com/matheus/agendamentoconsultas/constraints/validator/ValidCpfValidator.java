package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidCpf}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidCpf. Ela verifica
 * se cpf o está no formato válido.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class ValidCpfValidator implements ConstraintValidator<ValidCpf, String> {

    private static final Pattern CPF_PATTERN  = Pattern.compile("^(\\d{11})$");

    /**
     * Verifica se o CPF informado está no formato válido.
     *
     * @param cpf O CPF que será validado.
     * @param context O contexto de validação.
     * @return {@code true} se o CPF está no formato válido ou é nulo/vazio, {@code false} caso contrário.
     */
    @Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
        return cpf == null || cpf.trim().isEmpty() || CPF_PATTERN.matcher(cpf).matches();
	}
}