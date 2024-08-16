package br.com.matheus.agendamentoconsultas.base.clock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

@TestConfiguration
public class DataFixaConfig {

    @Bean
    public Clock clock() {
        return Clock.fixed(
                LocalDate.of(2024, 7, 21).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault()
        );
    }
}