package br.com.matheus.agendamentoconsultas.model.vo;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CPFTest {

    @Test
    void testConstructors() {
        assertThat(CPF.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(CPF.class, hasValidGettersAndSetters());
    }

    @Test
    void testEquals() {
        assertThat(CPF.class, hasValidBeanEquals());
    }

    @Test
    void testHashCode() {
        assertThat(CPF.class, hasValidBeanHashCode());
    }

    @Test
    void testToString() {
        final String expectedToString = "11111111111";
        CPF cpf = new CPF("11111111111");
        assertThat(cpf.toString(), equalTo(expectedToString));
    }
}