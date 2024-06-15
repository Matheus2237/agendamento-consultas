package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidEspecializacao;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ValidEspecializacaoValidator implements ConstraintValidator<ValidEspecializacao, String> {

	@Override
	public boolean isValid(String especializacao, ConstraintValidatorContext context) {
		return especializacao == null || especializacao.trim().isEmpty() || isEspecializacaoExistente(especializacao);
	}

	private boolean isEspecializacaoExistente(String especializacao) {
		return Stream.of(Especializacao.values())
				.map(Especializacao::toString)
				.anyMatch(esp -> esp.equals(especializacao));
	}
}