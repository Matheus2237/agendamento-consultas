package br.com.matheus.agendamentoconsultas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * <p>
 * Classe de configuração para codificação de caracteres
 * </p>
 * <p>
 * Essa classe define um bean do tipo {@link org.springframework.web.filter.CharacterEncodingFilter} que garante que todos as requisições e respostas
 * estarão codificadas em UTF-8.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since  1.0.0
 */
@Configuration
public class EncodingConfig {

    /**
     * Cria um bean {@link CharacterEncodingFilter} configurado para a codificação UTF-8
     * <p>
     * Este método define um bean Spring do tipo CharacterEncodingFilter. O filtro é configurado para usar a codificação
     * UTF-8 para ambas as requisições e respostas.
     *
     * @return Um bean {@link CharacterEncodingFilter} configurado para UTF-8
     */
    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}