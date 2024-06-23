package br.com.matheus.agendamentoconsultas.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;

class MedicoTest {

    @Test
    void testConstructors() {
        assertThat(Medico.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(Medico.class, hasValidGettersAndSetters());
    }
}