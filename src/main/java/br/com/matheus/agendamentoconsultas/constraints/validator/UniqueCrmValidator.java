package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCrm;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.UniqueCrm}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @UniqueCrm. Ela verifica
 * se o CRM informado já existe no banco de dados antes de permitir o valor.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class UniqueCrmValidator implements ConstraintValidator<UniqueCrm, String> {

	private final MedicoRepository medicoRepository;

	@Autowired
    public UniqueCrmValidator(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

	/**
	 * Verifica se o CRM informado é único.
	 *
	 * @param crm O CRM que será validado.
	 * @param context O contexto de validação.
	 * @return {@code true} se o CRM é único e não existe no banco de dados, {@code false} caso contrário.
	 */
    @Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return !medicoRepository.existsByCrmValue(crm);
	}
}