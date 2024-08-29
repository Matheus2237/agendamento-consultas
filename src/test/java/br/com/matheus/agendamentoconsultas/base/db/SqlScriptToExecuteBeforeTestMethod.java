package br.com.matheus.agendamentoconsultas.base.db;

import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * Anotação customizada para executar um script Sql antes do método de teste.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Target(METHOD)
@Retention(RUNTIME)
@Sql(scripts = "", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
public @interface SqlScriptToExecuteBeforeTestMethod {

    /**
     * Alias para a anotação {@link Sql}. Passa o valor para o atributo scripts.
     *
     * @return Uma String contendo o valor desejado para o atributo scripts.
     */
    @AliasFor(annotation = Sql.class, attribute = "scripts")
    String value();
}