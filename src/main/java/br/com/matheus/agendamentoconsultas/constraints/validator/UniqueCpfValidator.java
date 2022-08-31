package br.com.matheus.agendamentoconsultas.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCpf;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		return !pacienteRepository.existsByCpf(cpf);
	}
}
