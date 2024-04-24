package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidEspecialization;
import br.com.matheus.agendamentoconsultas.model.Especializacao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidEspecializationValidator implements ConstraintValidator<ValidEspecialization, String> {

	List<Especializacao> especializacoes = Arrays.asList(Especializacao.values());
	
	@Override
	public boolean isValid(String especializacao, ConstraintValidatorContext context) {
		for(Especializacao especializacaoEnum : especializacoes) {
			String especializacaoComparacao = especializacaoEnum.getName();
			if(especializacaoComparacao.contentEquals(especializacao)) {
				return true;
			}
		}
		return false;
	}
}
