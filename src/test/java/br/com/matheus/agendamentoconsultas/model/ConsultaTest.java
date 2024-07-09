package br.com.matheus.agendamentoconsultas.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class ConsultaTest {

    @BeforeAll
    static void setup() {
        Random random = new Random();
        int ano = random.nextInt(9999);
        int mes = random.nextInt(12) + 1;
        int dia = random.nextInt(28) + 1;
        int hora = random.nextInt(24);
        int minuto = random.nextInt(60);
        int segundo = 0;
        registerValueGenerator(() -> LocalDate.of(ano, mes, dia), LocalDate.class);
        registerValueGenerator(() -> LocalTime.of(hora, minuto, segundo), LocalTime.class);
    }

    @Test
    void testConstructors() {
        assertThat(Consulta.class, hasValidBeanConstructor());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(Consulta.class, hasValidGettersAndSetters());
    }
}