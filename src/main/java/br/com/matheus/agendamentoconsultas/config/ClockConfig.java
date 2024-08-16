package br.com.matheus.agendamentoconsultas.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * <p>
 * Classe de configuração para o bean {@link Clock}.
 * </p>
 * <p>
 * Essa classe define um bean do tipo {@link Clock}, que fornece a data e hora
 * atual do sistema de acordo com o fuso horário padrão. Isso é útil para
 * injeção de dependências em componentes que precisam de uma referência ao
 * tempo atual.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Configuration
public class ClockConfig {

    /**
     * Cria um bean {@link Clock} configurado para usar o fuso horário padrão
     * do sistema.
     * <p>
     * Este método define um bean Spring do tipo {@link Clock}, que pode ser
     * injetado em outras partes da aplicação para acessar a data e hora atual.
     * </p>
     *
     * @return Um bean {@link Clock} configurado para o fuso horário padrão.
     */
    @Bean
    @ConditionalOnMissingBean
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}