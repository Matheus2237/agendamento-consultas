package br.com.matheus.agendamentoconsultas.base;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Classe base para testes unitários que utilizam mocks. Gerencia a inicialização e encerramento dos mocks.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public abstract class MockedUnitTest {

    private AutoCloseable mocks;

    @BeforeEach
    void mocksSetUp() {
        mocks = openMocks(this);
    }

    @AfterEach
    @SneakyThrows
    void mocksTearDown() {
        mocks.close();
    }
}