package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCpf;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	private final PacienteRepository pacienteRepository;

	@Autowired
    public UniqueCpfValidator(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		return !pacienteRepository.existsByCpfValue(cpf);
	}
}