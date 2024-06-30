package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidDiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidDiaDaSemana}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidDiaDaSemana. Ela verifica se o dia da semana
 * informado é um valor válido de {@link br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana}.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public class ValidDiaDaSemanaValidator implements ConstraintValidator<ValidDiaDaSemana, String> {

    /**
     * Verifica se o dia da semana informado é válido.
     *
     * @param diaDaSemana O dia da semana que será validado.
     * @param context O contexto de validação.
     * @return {@code true} se o dia da semana é válido ou é nulo/vazio, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(String diaDaSemana, ConstraintValidatorContext context) {
        return isBlank(diaDaSemana) || isDiaDaSemanaExistente(diaDaSemana);
    }

    /**
     * Verifica se o dia da semana informado existe no enum {@link DiaDaSemana}.
     *
     * @param diaDaSemana O dia da semana que será verificado.
     * @return {@code true} se o dia da semana existe, {@code false} caso contrário.
     */
    private boolean isDiaDaSemanaExistente(String diaDaSemana) {
        return Stream.of(DiaDaSemana.values())
                .map(DiaDaSemana::toString)
                .anyMatch(esp -> esp.equals(diaDaSemana));
    }
}