package br.com.matheus.agendamentoconsultas.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private MedicoRepository medicoRepository;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !medicoRepository.existsByEmail(email);
	}
}
