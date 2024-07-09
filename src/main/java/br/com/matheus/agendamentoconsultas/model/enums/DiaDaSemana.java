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

    public static DiaDaSemana getDiaDaSemanaPelaData(final LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data com valor nulo");
        }
        int valorDiaDaSemana = data.getDayOfWeek().getValue();
        return getDiaDaSemanaPeloValorNumerico(valorDiaDaSemana);
    }

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