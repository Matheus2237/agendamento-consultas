package br.com.matheus.agendamentoconsultas.model.vo;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CRMTest {

    @Test
    void testConstructors() {
        assertThat(CRM.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(CRM.class, hasValidGettersAndSetters());
    }

    @Test
    void testEquals() {
        assertThat(CRM.class, hasValidBeanEquals());
    }

    @Test
    void testHashCode() {
        assertThat(CRM.class, hasValidBeanHashCode());
    }

    @Test
    void testToString() {
        final String expectedToString = "MG111111";
        CRM crm = new CRM("MG111111");
        assertThat(crm.toString(), equalTo(expectedToString));
    }
}