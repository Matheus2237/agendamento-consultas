package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidStringDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>
 * Validador para o constraint {@link ValidStringDate}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidDataRequisicaoConsulta. Ela verifica se a data
 * informada segue o padrão YYYY-MM-DD e se é uma data existente no calendário.
 * </p>
 * <p>
 * A data é validada com um padrão de regex e, em seguida, verificada quanto à sua existência no calendário, incluindo
 * consideração de anos bissextos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public class ValidStringDateValidator implements ConstraintValidator<ValidStringDate, String> {

    private static final Pattern YYYY_MM_DD_LOCAL_DATE_PATTERN = Pattern.compile("^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12]\\d|3[01])$");

    /**
     * Valida se a data fornecida é válida.
     *
     * @param data A data a ser validada
     * @param context O contexto de validação
     * @return {@code true} se a data é válida ou está em branco/nula, {@code false} caso contrário
     */
    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        return isBlank(data) || isDataValida(data);
    }

    /**
     * Verifica se a data fornecida é válida.
     *
     * @param data A data a ser verificada
     * @return {@code true} se a data é válida, {@code false} caso contrário
     */
    private boolean isDataValida(String data) {
        Matcher dataMatcher = YYYY_MM_DD_LOCAL_DATE_PATTERN.matcher(data);
        if (dataMatcher.matches()) {
            int ano = parseInt(dataMatcher.group(1));
            int mes = parseInt(dataMatcher.group(2));
            int dia = parseInt(dataMatcher.group(3));
            return isDataExistente(ano, mes, dia);
        }
        return false;
    }

    /**
     * Verifica se a data fornecida existe no calendário.
     *
     * @param ano O ano da data
     * @param mes O mês da data
     * @param dia O dia da data
     * @return {@code true} se a data existe, {@code false} caso contrário
     */
    private boolean isDataExistente(int ano, int mes, int dia) {
        return dia <= getDiaMaximoDoMesDeDeterminadoAno(mes, ano);
    }

    /**
     * Retorna o número máximo de dias em um mês específico de um ano específico.
     *
     * @param mes O mês da data
     * @param ano O ano da data
     * @return O número máximo de dias no mês
     */
    private int getDiaMaximoDoMesDeDeterminadoAno(int mes, int ano) {
        return switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isAnoBissexto(ano) ? 29 : 28;
            default -> throw new IllegalArgumentException("Valor inesperado, mes: " + mes);
        };
    }

    /**
     * Verifica se o ano fornecido é bissexto.
     *
     * @param ano O ano a ser verificado.
     * @return {@code true} se o ano é bissexto, {@code false} caso contrário.
     */
    private boolean isAnoBissexto(int ano) {
        return ano % 100 != 0 && ano % 4 == 0 || ano % 400 == 0;
    }
}
