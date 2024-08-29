package br.com.matheus.agendamentoconsultas.model.enums;

import java.time.LocalDate;

/**
 * <p>
 * Representa os dias da semana.
 * </p>
 * <p>
 * Esta enumeração é usada para definir os dias da semana para o horário de atendimento de médicos no sistema.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public enum DiaDaSemana {

    DOMINGO,
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO;

    /**
     * Obtém o dia da semana para a data especificada.
     *
     * @param data A data para a qual se deseja obter o dia da semana
     * @return O dia da semana correspondente à data fornecida
     * @throws IllegalArgumentException Se a data fornecida for nula
     */
    public static DiaDaSemana getDiaDaSemanaPelaData(final LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data com valor nulo");
        }
        int valorDiaDaSemana = data.getDayOfWeek().getValue();
        return getDiaDaSemanaPeloValorNumerico(valorDiaDaSemana);
    }

    /**
     * Obtém o dia da semana com base no valor numérico correspondente
     * (1 para SEGUNDA, 2 para TERCA, ..., 7 para DOMINGO).
     *
     * @param valorNumerico O valor numérico representando o dia da semana
     * @return O dia da semana correspondente ao valor numérico fornecido
     * @throws IllegalArgumentException Se o valor numérico não corresponder a nenhum dia da semana válido
     */
    public static DiaDaSemana getDiaDaSemanaPeloValorNumerico(final int valorNumerico) {
        return switch (valorNumerico) {
            case 1 -> SEGUNDA;
            case 2 -> TERCA;
            case 3 -> QUARTA;
            case 4 -> QUINTA;
            case 5 -> SEXTA;
            case 6 -> SABADO;
            case 7 -> DOMINGO;
            default -> throw new IllegalArgumentException("Dia da semana inexistente para o valor: " + valorNumerico);
        };
    }
}