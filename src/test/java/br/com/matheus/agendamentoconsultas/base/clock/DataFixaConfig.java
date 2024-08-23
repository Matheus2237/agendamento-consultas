package br.com.matheus.agendamentoconsultas.base.clock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Configuração de teste responsável por fornecer um {@link Clock} fixo.
 * Configura os testes para considerar a data atual como sendo 21 de julho de 2024.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@TestConfiguration
public class DataFixaConfig {

    /**
     * Cria um {@link Clock} fixo configurado para o dia 21 de julho de 2024.
     *
     * @return instância de {@link Clock} fixo.
     */
    @Bean
    public Clock clock() {
        return Clock.fixed(
                LocalDate.of(2024, 7, 21).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault()
        );
    }
}