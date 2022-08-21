package br.com.matheus.agendamentoconsultas.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.CrmUnica;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

@Component
public class CrmUnicaValidator implements ConstraintValidator<CrmUnica, Long> {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Override
	public boolean isValid(Long crm, ConstraintValidatorContext context) {
		return !medicoRepository.existsByCrm(crm);
	}
}
