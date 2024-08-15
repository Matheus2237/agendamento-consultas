package br.com.matheus.agendamentoconsultas.base;

import br.com.matheus.agendamentoconsultas.base.db.IntegrationTestDatabaseProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.util.TestSocketUtils;

import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class DatabaseProvidedIntegrationTest {

    private static final IntegrationTestDatabaseProvider testDatabase = IntegrationTestDatabaseProvider.instance();

    @DynamicPropertySource
    static void configureTestEnvironmentProperties(DynamicPropertyRegistry registry) {
        registry.add("server.port", TestSocketUtils::findAvailableTcpPort);
        testDatabase.configureDynamicPropertyRegistryToTestEnvironment(registry);
    }

    @BeforeAll
    public static void setUpAll() {
        LocaleContextHolder.setDefaultLocale(Locale.of("pt", "BR"));
        testDatabase.createSchema();
    }

    @AfterEach
    public void tearDown() {
        testDatabase.cleanSchema();
    }

    @AfterAll
    public static void tearDownAll() {
        testDatabase.dropSchema();
    }
}