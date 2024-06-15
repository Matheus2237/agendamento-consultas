package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.UniqueEmail;
import br.com.matheus.agendamentoconsultas.repository.EmailRepository;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UniqueEmailValidatorTest {

	@InjectMocks
	private UniqueEmailValidator validator;

	@Mock
	private UniqueEmail uniqueEmailMock;

	@Mock
	private JpaRepository<?, ?> jpaRepositoryMock;

	@Mock
	private ApplicationContext applicationContextMock;

	@Mock
	private EmailRepository emailRepositoryMock;

	@Mock
	private ConstraintValidatorContext constraintValidatorContextMock;

	private static final String EMAIL = "exemplo@email.com";

	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = openMocks(this);
		when(uniqueEmailMock.repository()).thenAnswer(invocation -> JpaRepository.class);
		when(applicationContextMock.getBean(JpaRepository.class)).thenReturn(jpaRepositoryMock);
		validator.initialize(uniqueEmailMock);
	}

	@AfterEach
	@SneakyThrows
	void tearDown() {
		mocks.close();
	}

	@Test
	void deveRetornarVerdadeiroAoPassarUmEmailUnicoNoSistema() {
		when(emailRepositoryMock.existsByEmail(EMAIL, jpaRepositoryMock)).thenReturn(false);
		boolean isValid = validator.isValid(EMAIL, constraintValidatorContextMock);
		assertTrue(isValid, "Deve retornar verdadeiro ao não possuir o email no sistema.");
	}

	@Test
	void deveRetornarFalsoAoPassarUmEmailJaExistenteNoSistema() {
		when(emailRepositoryMock.existsByEmail(EMAIL, jpaRepositoryMock)).thenReturn(true);
		boolean isValid = validator.isValid(EMAIL, constraintValidatorContextMock);
		assertFalse(isValid, "Deve retornar falso ao existir o email no sistema previamente.");
	}
}