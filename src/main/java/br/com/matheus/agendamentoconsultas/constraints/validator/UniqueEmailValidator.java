package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.repository.EmailRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private final EmailRepository emailRepository;
	private Class<? extends JpaRepository<?, ?>> repositoryClass;
	private final ApplicationContext applicationContext;

	@Autowired
	public UniqueEmailValidator(EmailRepository emailRepository, ApplicationContext applicationContext) {
        this.emailRepository = emailRepository;
        this.applicationContext = applicationContext;
    }

	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
		repositoryClass = constraintAnnotation.repository();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		JpaRepository<?, ?> repository = applicationContext.getBean(repositoryClass);
		return !emailRepository.existsByEmail(email, repository);
	}
}