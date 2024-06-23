package br.com.matheus.agendamentoconsultas.model.pk;

import org.junit.jupiter.api.Test;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.DOMINGO;
import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class HorarioAtendimentoPKTest {

    @Test
    void testConstructors() {
        assertThat(HorarioAtendimentoPK.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(HorarioAtendimentoPK.class, hasValidGettersAndSetters());
    }

    @Test
    void testEquals() {
        assertThat(HorarioAtendimentoPK.class, hasValidBeanEquals());
    }

    @Test
    void testHashCode() {
        assertThat(HorarioAtendimentoPK.class, hasValidBeanHashCode());
    }

    @Test
    void testToString() {
        final String expectedToString = "HorarioAtendimentoPK{medicoId=1, diaDaSemana=DOMINGO}";
        HorarioAtendimentoPK horarioAtendimentoPK = new HorarioAtendimentoPK(1L, DOMINGO);
        assertThat(horarioAtendimentoPK.toString(), equalTo(expectedToString));
    }
}