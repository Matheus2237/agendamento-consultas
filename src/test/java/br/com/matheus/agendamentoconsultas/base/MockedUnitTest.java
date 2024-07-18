package br.com.matheus.agendamentoconsultas.base;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.MockitoAnnotations.openMocks;

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