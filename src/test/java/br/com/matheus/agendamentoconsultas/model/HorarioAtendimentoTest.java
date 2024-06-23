package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Random;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.DOMINGO;
import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class HorarioAtendimentoTest {

    @BeforeAll
    static void setup() {
        Random random = new Random();
        int hora = random.nextInt(24);
        int minuto = random.nextInt(60);
        int segundo = 0;
        Long medicoId = random.nextLong();
        registerValueGenerator(() -> LocalTime.of(hora, minuto, segundo), LocalTime.class);
        registerValueGenerator(() -> new HorarioAtendimentoPK(medicoId, DOMINGO), HorarioAtendimentoPK.class);
    }

    @Test
    void testConstructors() {
        assertThat(HorarioAtendimento.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(HorarioAtendimento.class, hasValidGettersAndSetters());
    }
}