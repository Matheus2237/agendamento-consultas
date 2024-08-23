package br.com.matheus.agendamentoconsultas.base.json;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Anotação para fornecer fontes de parâmetros de URL JSON como argumentos em testes parametrizados.
 * Delegada ao {@link HttpUrlParamJsonSourceProvider}.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@ArgumentsSource(HttpUrlParamJsonSourceProvider.class)
public @interface HttpUrlParamJsonSource {

    String value();
}
