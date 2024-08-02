package br.com.matheus.agendamentoconsultas.base.json;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, METHOD})
@Retention(RUNTIME)
@ArgumentsSource(HttpUrlParamJsonSourceProvider.class)
public @interface HttpUrlParamJsonSource {

    String value();
}
