package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidCrm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.matheus.agendamentoconsultas.constraints.validator.UFValidator.isValidUF;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidCrm}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidCrm. Ela verifica
 * se crm o está no formato válido.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class ValidCrmValidator implements ConstraintValidator<ValidCrm, String> {

    private static final Pattern CRM_PATTERN = Pattern.compile("^([A-Z]{2})(\\d{6})$");

    /**
     * Verifica se o CRM informado está no formato válido.
     *
     * @param crm     O CRM que será validado.
     * @param context O contexto de validação.
     * @return {@code true} se o CRM está no formato válido ou é nulo/vazio, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(String crm, ConstraintValidatorContext context) {
        return isBlank(crm) || isValid(crm);
    }

    private boolean isValid(String crm) {
        Matcher crmMatcher = CRM_PATTERN.matcher(crm);
        if (crmMatcher.matches()) {
            String uf = crmMatcher.group(1);
            return isValidUF(uf);
        }
        return false;
    }
}