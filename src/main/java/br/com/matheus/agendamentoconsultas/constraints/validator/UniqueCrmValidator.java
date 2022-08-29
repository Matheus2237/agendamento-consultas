package br.com.matheus.agendamentoconsultas.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCrm;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

@Component
public class UniqueCrmValidator implements ConstraintValidator<UniqueCrm, String> {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return !medicoRepository.existsByCrm(crm);
	}
}
