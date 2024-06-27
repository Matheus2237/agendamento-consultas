package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueCpf;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.UniqueCpf}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @UniqueCpf. Ela verifica
 * se o CPF informado já existe no banco de dados antes de permitir o valor.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	private final PacienteRepository pacienteRepository;

	@Autowired
    public UniqueCpfValidator(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

	/**
	 * Verifica se o CPF informado é único.
	 *
	 * @param cpf O CPF que será validado.
	 * @param context O contexto de validação.
	 * @return {@code true} se o CPF é único e não existe no banco de dados, {@code false} caso contrário.
	 */
    @Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		return !pacienteRepository.existsByCpfValue(cpf);
	}
}