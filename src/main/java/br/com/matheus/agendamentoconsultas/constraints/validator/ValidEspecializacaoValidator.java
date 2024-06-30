package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidEspecializacao;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidEspecializacao}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidEspecializacao. Ela verifica se a especialização
 * informada é um valor válido de {@link br.com.matheus.agendamentoconsultas.model.enums.Especializacao}.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class ValidEspecializacaoValidator implements ConstraintValidator<ValidEspecializacao, String> {

	/**
	 * Verifica se a especialização informada é válida.
	 *
	 * @param especializacao A especialização que será validada.
	 * @param context O contexto de validação.
	 * @return {@code true} se a especialização é válida ou é nula/vazia, {@code false} caso contrário.
	 */
	@Override
	public boolean isValid(String especializacao, ConstraintValidatorContext context) {
		return especializacao == null || especializacao.trim().isEmpty() || isEspecializacaoExistente(especializacao);
	}

	/**
	 * Verifica se a especialização informada existe no enum {@link Especializacao}.
	 *
	 * @param especializacao A especialização que será verificada.
	 * @return {@code true} se a especialização existe, {@code false} caso contrário.
	 */
	private boolean isEspecializacaoExistente(String especializacao) {
		return Stream.of(Especializacao.values())
				.map(Especializacao::toString)
				.anyMatch(esp -> esp.equals(especializacao));
	}
}