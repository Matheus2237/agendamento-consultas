package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.base.AbstractValidacaoAgendamentoConsultaBaseTest;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import br.com.matheus.agendamentoconsultas.repository.HorarioAtendimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana.getDiaDaSemanaPelaData;
import static org.mockito.Mockito.when;

class ValidacaoHorarioConsultaDentroDoHorarioDeAtendimentoTest extends AbstractValidacaoAgendamentoConsultaBaseTest {

    @InjectMocks
    private ValidacaoHorarioConsultaDentroDoHorarioDeAtendimento validacao;

    @Mock
    private HorarioAtendimentoRepository horarioAtendimentoRepositoryMock;

    @ParameterizedTest
    @MethodSource("provisionaHorariosDeAtendimentoValidos")
    void deveNaoRetornarNadaAoValidarQueOHorarioDaConsultaECompativelComOHorarioDeAtendimentoDo(HorarioAtendimento horarioAtendimento) {
        Consulta consulta = getEntidadeConsulta();
        when(horarioAtendimentoRepositoryMock.findByPrimaryKeyMedicoIdAndPrimaryKeyDiaDaSemana(medicoId, diaDaSemana))
                .thenReturn(Optional.of(horarioAtendimento));
        Assertions.assertDoesNotThrow(() -> validacao.validar(consulta),
                "Nenhuma exceção deve ser lançada.");
    }

    public static Stream<HorarioAtendimento> provisionaHorariosDeAtendimentoValidos() {
        return Stream.of(
                getHorarioAtendimento(diaDaSemana, horarioConsulta.minusMinutes(30), horarioConsulta.plusMinutes(30)),
                getHorarioAtendimento(diaDaSemana, horarioConsulta, horarioConsulta.plusMinutes(30)),
                getHorarioAtendimento(diaDaSemana, horarioConsulta.minusMinutes(30), horarioConsulta)
        );
    }

    @ParameterizedTest
    @MethodSource("provisionaHorariosDeAtendimentoInvalidos")
    void deveLancarUmaExcecaoQuandoAConsultaNaoEstiverCompativelComOsHorariosDeAtendimentoDoMedico(HorarioAtendimento horarioAtendimento) {
        Consulta consulta = getEntidadeConsulta();
        when(horarioAtendimentoRepositoryMock.findByPrimaryKeyMedicoIdAndPrimaryKeyDiaDaSemana(medicoId, diaDaSemana))
                .thenReturn(Optional.of(horarioAtendimento));
        Assertions.assertThrows(ConsultaNaoPodeSerMarcadaException.class,
                () -> validacao.validar(consulta),
                "Deve lançar exceçao.");
    }

    public static Stream<HorarioAtendimento> provisionaHorariosDeAtendimentoInvalidos() {
        return Stream.of(
                getHorarioAtendimento(diaDaSemana, horarioConsulta.plusMinutes(30), horarioConsulta.plusMinutes(90)),
                getHorarioAtendimento(diaDaSemana, horarioConsulta.minusMinutes(90), horarioConsulta.minusMinutes(30))
        );
    }

    @Test
    void deveLancarUmaExcecaoQuandoNaoEncontrarOHorarioDeAtendimentoDoMedicoParaAqueleDiaNoSistema() {
        Consulta consulta = getEntidadeConsulta();
        DiaDaSemana diaDaSemana = getDiaDaSemanaPelaData(consulta.getData());
        when(horarioAtendimentoRepositoryMock.findByPrimaryKeyMedicoIdAndPrimaryKeyDiaDaSemana(medicoId, diaDaSemana)).thenReturn(Optional.empty());
        Assertions.assertThrows(ConsultaNaoPodeSerMarcadaException.class,
                () -> validacao.validar(consulta),
                "Deve lançar exceção.");
    }

    private static HorarioAtendimento getHorarioAtendimento(DiaDaSemana diaDaSemana, LocalTime horarioInicial, LocalTime horarioFinal) {
        return new HorarioAtendimento(medico, diaDaSemana, horarioInicial, horarioFinal);
    }
}