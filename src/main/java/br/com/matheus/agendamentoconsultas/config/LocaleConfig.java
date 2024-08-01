package br.com.matheus.agendamentoconsultas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * <p>
 * Classe de configuração para a resolução de locais
 * </p>
 * <p>
 * Esta classe define um bean do tipo
 * {@link org.springframework.web.servlet.LocaleResolver} que configura o local padrão
 * para o Brasil (pt-BR).
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Configuration
public class LocaleConfig {

    /**
     * Cria um bean {@link LocaleResolver} configurado para o local padrão pt-BR
     * <p>
     * Este método define um bean Spring do tipo LocaleResolver. O resolvedor de local
     * é configurado para usar o local padrão do Brasil (pt-BR).
     *
     * @return Um bean {@link LocaleResolver} configurado para pt-BR
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.of("pt", "BR"));
        return slr;
    }
}