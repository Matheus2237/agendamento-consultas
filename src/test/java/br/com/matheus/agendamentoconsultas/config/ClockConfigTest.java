package br.com.matheus.agendamentoconsultas.config;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClockConfigTest {

    @Test
    void deveConfigurarUmClockComFusoHorarioPadrao() {
        ClockConfig clockConfig = new ClockConfig();
        Clock clock = clockConfig.clock();
        assertEquals(ZoneId.systemDefault(), clock.getZone(), "O Clock deve usar o fuso horário padrão do sistema.");
    }
}