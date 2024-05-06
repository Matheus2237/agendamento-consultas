package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCrm;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCrmValidator implements ConstraintValidator<UniqueCrm, String> {

	private final MedicoRepository medicoRepository;

	@Autowired
    public UniqueCrmValidator(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return !medicoRepository.existsByCrm(crm);
	}
}