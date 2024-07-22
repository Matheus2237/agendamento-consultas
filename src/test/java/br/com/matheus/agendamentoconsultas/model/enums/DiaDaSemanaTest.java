package br.com.matheus.agendamentoconsultas.model.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.getDiaDaSemanaPelaData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiaDaSemanaTest {

    @ParameterizedTest
    @MethodSource("provisionaDiasDaSemana")
    void deveRetornarODiaDaSemanaDeUmaDeterminadaData(DiaDaSemana diaDaSemana, int ano, int mes, int dia) {
        LocalDate data = LocalDate.of(ano, mes, dia);
        assertEquals(diaDaSemana, getDiaDaSemanaPelaData(data));
    }

    public static Stream<Arguments> provisionaDiasDaSemana() {
        return Stream.of(
                Arguments.of(DiaDaSemana.DOMINGO, 2023, 7, 2),
                Arguments.of(DiaDaSemana.SEGUNDA, 2023, 7, 3),
                Arguments.of(DiaDaSemana.TERCA, 2023, 7, 4),
                Arguments.of(DiaDaSemana.QUARTA, 2023, 7, 5),
                Arguments.of(DiaDaSemana.QUINTA, 2023, 7, 6),
                Arguments.of(DiaDaSemana.SEXTA, 2023, 7, 7),
                Arguments.of(DiaDaSemana.SABADO, 2023, 7, 8)
        );
    }

    @Test
    void deveLancarUmaIllegalArgumentExceptionParaUmaDataNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            getDiaDaSemanaPelaData(null);
        }, "Deveria ter lançado IllegalArgumentException para data nula.");
    }

    @Test
    void deveLancarUmaIllegalArgumentExceptionParaUmValorDeDiaDaSemanaQueNaoExiste() {
        assertThrows(IllegalArgumentException.class, () -> {
            DiaDaSemana.getDiaDaSemanaPeloValorNumerico(8);
        }, "Deve lançar uma IllegalArgumentException para valores maiores que 7.");
    }
}