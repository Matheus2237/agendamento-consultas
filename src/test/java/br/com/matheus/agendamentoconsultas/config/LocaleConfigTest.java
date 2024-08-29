package br.com.matheus.agendamentoconsultas.config;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class LocaleConfigTest {

    @Test
    void deveConfigurarUmResolverDeLocaleParaPtBR() {
        LocaleConfig localeConfig = new LocaleConfig();
        LocaleResolver localeResolver = localeConfig.localeResolver();
        assertInstanceOf(SessionLocaleResolver.class, localeResolver, "O resolver deve ser uma instância de SessionLocaleResolver.");
        SessionLocaleResolver resolver = (SessionLocaleResolver) localeResolver;
        Locale locale = ReflectionTestUtils.invokeMethod(resolver, "getDefaultLocale");
        assertEquals(Locale.of("pt", "BR"), locale, "O local padrão deve ser pt-BR.");
    }
}