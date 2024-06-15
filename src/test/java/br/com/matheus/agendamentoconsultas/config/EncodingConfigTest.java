package br.com.matheus.agendamentoconsultas.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncodingConfigTest {

    @Test
    void deveConfigurarUmFiltroDeEncodingParaUTF8() {
        EncodingConfig encodingConfig = new EncodingConfig();
        CharacterEncodingFilter filter = encodingConfig.characterEncodingFilter();
        assertEquals("UTF-8", filter.getEncoding(), "O encoding deve ser UTF-8.");
        assertTrue(filter.isForceRequestEncoding(), "Deve forçar o encoding no request.");
        assertTrue(filter.isForceResponseEncoding(), "Deve forcar o encoding na response.");
    }
}