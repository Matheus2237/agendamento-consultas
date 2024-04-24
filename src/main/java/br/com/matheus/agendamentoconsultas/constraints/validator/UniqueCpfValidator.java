package br.com.matheus.agendamentoconsultas.constraints.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCpf;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		return !pacienteRepository.existsByCpf(cpf);
	}
}
