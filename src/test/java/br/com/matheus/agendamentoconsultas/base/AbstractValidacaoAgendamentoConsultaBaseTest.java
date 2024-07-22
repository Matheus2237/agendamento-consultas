package br.com.matheus.agendamentoconsultas.base;

import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.time.LocalTime;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.getDiaDaSemanaPelaData;

public abstract class AbstractValidacaoAgendamentoConsultaBaseTest extends MockedUnitTest {

    protected static Long medicoId;
    protected static Long pacienteId;
    protected static Medico medico;
    protected static Paciente paciente;
    protected static LocalDate data;
    protected static LocalTime horarioConsulta;
    protected static DiaDaSemana diaDaSemana;

    @BeforeAll
    static void setUpBeforeAll() {
        medicoId = 1L;
        pacienteId = 1L;
        medico = Medico.builder().id(medicoId).build();
        paciente = Paciente.builder().id(pacienteId).build();
        data = LocalDate.now().plusDays(1);
        horarioConsulta = LocalTime.parse("10:00");
        diaDaSemana = getDiaDaSemanaPelaData(data);
    }

    protected Consulta getEntidadeConsulta() {
        return Consulta.builder()
                .medico(medico)
                .paciente(paciente)
                .data(data)
                .horario(horarioConsulta)
                .build();
    }
}