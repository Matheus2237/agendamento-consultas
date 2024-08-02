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

    private static MySQLContainer<?> getConfiguredMysqlContainerInstance() {
        return new MySQLContainer<>(MYSQL_IMAGE)
                .withDatabaseName(DB_NAME)
                .withUsername(DB_USER)
                .withPassword(DB_PASSWORD);
    }

    private static HikariDataSource getConfiguredHikariDataSourceUsingMysqlContainerProperties() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mySQLContainer.getJdbcUrl());
        hikariConfig.setUsername(mySQLContainer.getUsername());
        hikariConfig.setPassword(mySQLContainer.getPassword());
        hikariConfig.setDriverClassName(mySQLContainer.getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }

    public static IntegrationTestDatabaseProvider instance() {
        return IntegrationTestDatabaseHolder.INSTANCE;
    }

    private static class IntegrationTestDatabaseHolder {
        private static final IntegrationTestDatabaseProvider INSTANCE = new IntegrationTestDatabaseProvider();
    }

    public void configureDynamicPropertyRegistryToTestEnvironment(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mySQLContainer::getDriverClassName);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> HIBERNATE_DDL_AUTO);
    }

    public void createSchema() {
        executeScriptInDatabase(SCHEMA_CREATION_SCRIPT);
    }

    public void cleanSchema() {
        executeScriptInDatabase(SCHEMA_CLEAN_SCRIPT);
    }

    public void dropSchema() {
        executeScriptInDatabase(SCHEMA_DROP_SCRIPT);
    }

    private void executeScriptInDatabase(Resource sqlScript) {
        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            ScriptUtils.executeSqlScript(connection, sqlScript);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void freeConnection() {
        mySQLContainer.close();
    }
}