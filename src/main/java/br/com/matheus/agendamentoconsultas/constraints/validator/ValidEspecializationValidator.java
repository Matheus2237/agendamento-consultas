package br.com.matheus.agendamentoconsultas.constraints.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.ValidEspecialization;
import br.com.matheus.agendamentoconsultas.model.Especializacao;

@Component
public class ValidEspecializationValidator implements ConstraintValidator<ValidEspecialization, String> {

	List<Especializacao> especializacoes = Arrays.asList(Especializacao.values());
	
	@Override
	public boolean isValid(String especializacao, ConstraintValidatorContext context) {
		for(Especializacao escpecializacaoEnum : especializacoes) {
			String especializacaoComparacao = Especializacao.enumToString(escpecializacaoEnum).toUpperCase();
			if(especializacao == especializacaoComparacao) {
				return true;
			}
		}
		return false;
	}
}
