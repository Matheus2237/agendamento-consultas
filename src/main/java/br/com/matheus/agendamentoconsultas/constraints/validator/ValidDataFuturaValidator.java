package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidDataFutura;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidDataFutura}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidDataFutura. Ela verifica se a data
 * fornecida é uma data futura, comparando-a com a data atual.
 * </p>
 * <p>
 * A data é considerada válida se for posterior à data atual.
 * </p>
 *
 * @see jakarta.validation.ConstraintValidator
 * @see jakarta.validation.ConstraintValidatorContext
 * @see java.time.LocalDate
 * @see br.com.matheus.agendamentoconsultas.constraints.ValidDataFutura
 * @see br.com.matheus.agendamentoconsultas.constraints.validator.ValidDataRequisicaoConsultaValidator
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public class ValidDataFuturaValidator implements ConstraintValidator<ValidDataFutura, String> {

    /**
     * Valida se a data fornecida é uma data futura.
     *
     * @param data A data a ser validada
     * @param context O contexto de validação
     * @return {@code true} se a data é futura, {@code false} caso contrário
     */
    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        return isDataDaConsultaAmanhaOuPosterior(data);
    }

    /**
     * Verifica se a data fornecida é posterior à data atual.
     *
     * @param data A data a ser verificada
     * @return {@code true} se a data é posterior à data atual, {@code false} caso contrário.
     */
    private boolean isDataDaConsultaAmanhaOuPosterior(String data) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataConsulta = LocalDate.parse(data);
        return dataConsulta.isAfter(hoje);
    }
}