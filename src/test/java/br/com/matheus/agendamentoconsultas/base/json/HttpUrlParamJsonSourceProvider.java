package br.com.matheus.agendamentoconsultas.base.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Provedor de argumentos que lê dados de um arquivo JSON e os fornece para testes parametrizados.
 * Espera que o JSON contenha uma lista de objetos no seu root.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public final class HttpUrlParamJsonSourceProvider extends HttpJsonSourceProvider
        implements ArgumentsProvider, AnnotationConsumer<HttpUrlParamJsonSource> {

    private String jsonFilePath;

    @Override
    public void accept(HttpUrlParamJsonSource httpUrlParamJsonSource) {
        this.jsonFilePath = httpUrlParamJsonSource.value();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return provideArguments(jsonFilePath);
    }

    protected Arguments parseNodeArguments(JsonNode node) {
        String request = node.path("url").asText();
        String expectedResponse = node.path("expectedResponse").toString();
        return Arguments.of(request, expectedResponse);
    }
}