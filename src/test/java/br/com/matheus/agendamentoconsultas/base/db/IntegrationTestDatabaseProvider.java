package br.com.matheus.agendamentoconsultas.base.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

/**
 * Provedor de banco de dados para testes de integração utilizando o Testcontainers e configuração Hikari.
 * Gerencia a configuração, criação, limpeza e destruição do schema durante os testes.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@NoArgsConstructor(access = PRIVATE)
public class IntegrationTestDatabaseProvider {

    private static final String MYSQL_IMAGE = "mysql:8.0";
    private static final String DB_NAME = "agendamento-consultas-test";
    private static final String DB_USER = "tester";
    private static final String DB_PASSWORD = "testpass";
    private static final String HIBERNATE_DDL_AUTO = "validate";

    private static final Resource SCHEMA_CREATION_SCRIPT = new ClassPathResource("sql/setup/create.sql");
    private static final Resource SCHEMA_CLEAN_SCRIPT = new ClassPathResource("sql/setup/clean.sql");
    private static final Resource SCHEMA_DROP_SCRIPT = new ClassPathResource("sql/setup/drop.sql");

    @Container
    public static final MySQLContainer<?> mySQLContainer;
    private static final DataSource dataSource;

    static {
        mySQLContainer = getConfiguredMysqlContainerInstance();
        mySQLContainer.start();
        dataSource = getConfiguredHikariDataSourceUsingMysqlContainerProperties();
    }

    /**
     * Instancia um test container de banco de dados configurado para os testes de integração.
     *
     * @return Um test colntainer MySQL configurado com as propriedades do ambiente de teste.
     */
    private static MySQLContainer<?> getConfiguredMysqlContainerInstance() {
        return new MySQLContainer<>(MYSQL_IMAGE)
                .withDatabaseName(DB_NAME)
                .withUsername(DB_USER)
                .withPassword(DB_PASSWORD);
    }

    /**
     * Configura um data source Hikari baseado nas propriedades do MySQLContainer
     *
     * @return Um HikariDataSource configurado com as propriedades do container de banco de dados.
     */
    private static HikariDataSource getConfiguredHikariDataSourceUsingMysqlContainerProperties() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mySQLContainer.getJdbcUrl());
        hikariConfig.setUsername(mySQLContainer.getUsername());
        hikariConfig.setPassword(mySQLContainer.getPassword());
        hikariConfig.setDriverClassName(mySQLContainer.getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }

    /**
     * Singleton para acessar a instância da classe.
     *
     * @return Uma instância única de IntegrationDatabaseProvider.
     */
    public static IntegrationTestDatabaseProvider instance() {
        return IntegrationTestDatabaseHolder.INSTANCE;
    }

    private static class IntegrationTestDatabaseHolder {
        private static final IntegrationTestDatabaseProvider INSTANCE = new IntegrationTestDatabaseProvider();
    }

    /**
     * Configura o registro dinâmico de propriedades para o ambiente de teste.
     *
     * @param registry o registro dinâmico de propriedades.
     */
    public void configureDynamicPropertyRegistryToTestEnvironment(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mySQLContainer::getDriverClassName);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> HIBERNATE_DDL_AUTO);
    }

    /**
     * Cria o schema do banco de dados utilizado nos testes.
     */
    public void createSchema() {
        executeScriptInDatabase(SCHEMA_CREATION_SCRIPT);
    }

    /**
     * Limpa o schema do banco de dados ao final de cada teste.
     */
    public void cleanSchema() {
        executeScriptInDatabase(SCHEMA_CLEAN_SCRIPT);
    }

    /**
     * Remove o schema do banco de dados ao final de todos os testes.
     */
    public void dropSchema() {
        executeScriptInDatabase(SCHEMA_DROP_SCRIPT);
    }

    /**
     * Executa o script no banco de dados configurado.
     *
     * @param sqlScript O Script a ser exectado.
     */
    private void executeScriptInDatabase(Resource sqlScript) {
        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            ScriptUtils.executeSqlScript(connection, sqlScript);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}