package br.com.matheus.agendamentoconsultas.model.enums;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DiaDaSemanaTest {

    @Test
    void deveRetornarODiaDaSemanaDeUmaDeterminadaData() {
        assertAll("Dias da semana para determinadas datas.",
                () -> assertEquals(DiaDaSemana.DOMINGO, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 2)), "Falhou para a data: 2023-07-02."),
                () -> assertEquals(DiaDaSemana.SEGUNDA, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 3)), "Falhou para a data: 2023-07-03."),
                () -> assertEquals(DiaDaSemana.TERCA, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 4)), "Falhou para a data: 2023-07-04."),
                () -> assertEquals(DiaDaSemana.QUARTA, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 5)), "Falhou para a data: 2023-07-05."),
                () -> assertEquals(DiaDaSemana.QUINTA, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 6)), "Falhou para a data: 2023-07-06."),
                () -> assertEquals(DiaDaSemana.SEXTA, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 7)), "Falhou para a data: 2023-07-07."),
                () -> assertEquals(DiaDaSemana.SABADO, DiaDaSemana.getDiaDaSemanaPelaData(LocalDate.of(2023, 7, 8)), "Falhou para a data: 2023-07-08.")
        );
    }

    @Test
    void deveLancarUmaIllegalArgumentExceptionParaUmaDataNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            DiaDaSemana.getDiaDaSemanaPelaData(null);
        }, "Deveria ter lançado IllegalArgumentException para data nula.");
    }

    @Test
    void deveLancarUmaIllegalArgumentExceptionParaUmValorDeDiaDaSemanaQueNaoExiste() {
        assertThrows(IllegalArgumentException.class, () -> {
            DiaDaSemana.getDiaDaSemanaPeloValorNumerico(8);
        }, "Deveria ter lançado IllegalArgumentException para o valor 8.");
    }
}