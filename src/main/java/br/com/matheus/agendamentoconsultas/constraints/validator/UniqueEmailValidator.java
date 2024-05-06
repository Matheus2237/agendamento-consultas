package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private final MedicoRepository medicoRepository;

	@Autowired
    public UniqueEmailValidator(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !medicoRepository.existsByEmail(email);
	}
}