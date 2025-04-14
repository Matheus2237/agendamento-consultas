package br.com.matheus.agendamentoconsultas.base;

import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.time.LocalTime;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.getDiaDaSemanaPelaData;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Classe base para testes de validação de agendamento de consultas, configurando entidades e dados necessários.
 * Usada para preparar o contexto de testes com valores iniciais comuns.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
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

        medico = spy(new Medico());
        paciente = spy(new Paciente());

        when(medico.getId()).thenReturn(medicoId);
        when(paciente.getId()).thenReturn(pacienteId);

        data = LocalDate.now().plusDays(1);
        horarioConsulta = LocalTime.parse("10:00");
        diaDaSemana = getDiaDaSemanaPelaData(data);
    }

    /**
     * Cria e retorna uma instância de {@link Consulta} com valores pré-configurados.
     *
     * @return instância de {@link Consulta}.
     */
    protected Consulta getEntidadeConsulta() {
        return new Consulta(medico, paciente, data, horarioConsulta);
    }
}