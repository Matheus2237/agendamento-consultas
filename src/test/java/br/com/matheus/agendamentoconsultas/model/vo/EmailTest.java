package br.com.matheus.agendamentoconsultas.model.vo;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class EmailTest {

    @Test
    void testConstructors() {
        assertThat(Email.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
            assertThat(Email.class, hasValidGettersAndSetters());
    }

    @Test
    void testEquals() {
        assertThat(Email.class, hasValidBeanEquals());
    }

    @Test
    void testHashCode() {
        assertThat(Email.class, hasValidBeanHashCode());
    }

    @Test
    void testToString() {
        final String expectedToString = "user@email.com";
        Email email = new Email("user@email.com");
        assertThat(email.toString(), equalTo(expectedToString));
    }
}