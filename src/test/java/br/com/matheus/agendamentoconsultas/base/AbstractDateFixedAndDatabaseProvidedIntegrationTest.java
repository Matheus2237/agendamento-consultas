package br.com.matheus.agendamentoconsultas.base;

import br.com.matheus.agendamentoconsultas.base.clock.DataFixaConfig;
import br.com.matheus.agendamentoconsultas.base.db.IntegrationTestDatabaseProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.util.TestSocketUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

/**
 * Classe abstrata para testes de integração com configuração de banco de dados e data fixa.
 * Esta classe base combina a configuração de um banco de dados MySQL em container, além de
 * fornecer um {@link DataFixaConfig} fixo para controlar a data em testes.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(DataFixaConfig.class)
public abstract class AbstractDateFixedAndDatabaseProvidedIntegrationTest {

    private static final IntegrationTestDatabaseProvider testDatabase = IntegrationTestDatabaseProvider.instance();

    @Autowired
    protected MockMvc mockMvc;

    /**
     * Configura as propriedades dinâmicas para o ambiente de teste, como a porta do servidor e as propriedades do banco de dados.
     *
     * @param registry o registro dinâmico de propriedades.
     */
    @DynamicPropertySource
    static void configureTestEnvironmentProperties(DynamicPropertyRegistry registry) {
        registry.add("server.port", TestSocketUtils::findAvailableTcpPort);
        testDatabase.configureDynamicPropertyRegistryToTestEnvironment(registry);
    }

    @BeforeAll
    static void setUpAll() {
        LocaleContextHolder.setDefaultLocale(Locale.of("pt", "BR"));
        testDatabase.createSchema();
    }

    @AfterEach
    void tearDown() {
        testDatabase.cleanSchema();
    }

    @AfterAll
    static void tearDownAll() {
        testDatabase.dropSchema();
    }
}