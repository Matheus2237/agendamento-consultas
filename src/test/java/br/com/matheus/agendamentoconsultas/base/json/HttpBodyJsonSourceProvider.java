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

public class HttpBodyJsonSourceProvider implements ArgumentsProvider, AnnotationConsumer<HttpBodyJsonSource> {

    private String jsonFilePath;

    @Override
    public void accept(HttpBodyJsonSource httpBodyJsonSource) {
        this.jsonFilePath = httpBodyJsonSource.value();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(jsonFilePath)).getPath())));
        JsonNode jsonNode = objectMapper.readTree(jsonContent);

        if (!jsonNode.isArray()) {
            throw new IllegalArgumentException("The JSON file must contain a list of objects at the root.");
        }

        return StreamSupport.stream(jsonNode.spliterator(), false)
                .map(node -> {
                    String request = node.path("request").toString();
                    String expectedResponse = node.path("expectedResponse").toString();
                    return Arguments.of(request, expectedResponse);
                });
    }
}