package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.repository.EmailRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.UniqueEmail}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @UniqueEmail. Ela verifica
 * se o e-mail informado já existe no banco de dados antes de permitir o valor.
 * </p>
 * <p>
 * Esse validator permite especificar qual repositório JPA deve ser usado para a verificação.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
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

    /**
     * Inicializa o validador com a classe do repositório especificada na anotação.
     *
     * @param constraintAnnotation A anotação @UniqueEmail que contém a classe do repositório.
     */
    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        repositoryClass = constraintAnnotation.repository();
    }

    /**
     * Verifica se o e-mail informado é único.
     *
     * @param email   O e-mail que será validado.
     * @param context O contexto de validação.
     * @return {@code true} se o e-mail é único e não existe no banco de dados, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        JpaRepository<?, ?> repository = applicationContext.getBean(repositoryClass);
        return !emailRepository.existsByEmail(email, repository);
    }
}