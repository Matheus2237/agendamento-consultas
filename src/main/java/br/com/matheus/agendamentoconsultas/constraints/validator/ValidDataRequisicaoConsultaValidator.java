package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidDataRequisicaoConsulta;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValidDataRequisicaoConsultaValidator implements ConstraintValidator<ValidDataRequisicaoConsulta, String> {

    private static final Pattern YYYY_MM_DD_LOCAL_DATE_PATTERN = Pattern.compile("^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12]\\d|3[01])$");

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        return isBlank(data) || isDataValida(data);
    }

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

    private boolean isDataExistente(int ano, int mes, int dia) {
        return dia <= getDiaMaximoDoMesDeDeterminadoAno(mes, ano);
    }

    private int getDiaMaximoDoMesDeDeterminadoAno(int mes, int ano) {
        return switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isAnoBissexto(ano) ? 29 : 28;
            default -> throw new IllegalArgumentException("Valor inesperado, mes: " + mes);
        };
    }

    private boolean isAnoBissexto(int ano) {
        return ano % 100 != 0 && ano % 4 == 0 || ano % 400 == 0;
    }
}
