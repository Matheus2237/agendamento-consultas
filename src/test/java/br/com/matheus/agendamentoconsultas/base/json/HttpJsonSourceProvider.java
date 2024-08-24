package br.com.matheus.agendamentoconsultas.base.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

public abstract class HttpJsonSourceProvider {

    protected Stream<? extends Arguments> provideArguments(String jsonFilePath) {
        JsonNode jsonNode = parseFileToJsonNode(jsonFilePath);
        if (!jsonNode.isArray()) {
            throw new IllegalArgumentException("The JSON file must contain a list of objects at the root.");
        }
        return StreamSupport.stream(jsonNode.spliterator(), false)
                .map(this::parseNodeArguments);
    }

    private JsonNode parseFileToJsonNode(String jsonFilePath) {
        try {
            String jsonContent = readFileAsString(jsonFilePath);
            return new ObjectMapper().readTree(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFileAsString(String jsonFileLocation) throws IOException {
        URL jsonFileResource = getClass().getClassLoader().getResource(jsonFileLocation);
        Path jsonPath = get(requireNonNull(jsonFileResource).getPath());
        return new String(readAllBytes(jsonPath));
    }

    protected abstract Arguments parseNodeArguments(JsonNode jsonNode);
}