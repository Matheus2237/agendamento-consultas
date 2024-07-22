package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.base.AbstractValidacaoAgendamentoConsultaBaseTest;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.model.Consulta;
import br.com.matheus.agendamentoconsultas.repository.ConsultaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

class ValidacaoUmaConsultaPorDiaPorPacienteTest extends AbstractValidacaoAgendamentoConsultaBaseTest {

    @InjectMocks
    private ValidacaoUmaConsultaPorDiaPorPaciente validacao;

    @Mock
    private ConsultaRepository consultaRepositoryMock;

    @Test
    void deveNaoRetornarNadaAoValidarQueOPacienteNaoPossuiUmaConsultaAgendadaParaAqueleDia() {
        Consulta consulta = getEntidadeConsulta();
        when(consultaRepositoryMock.existsByPacienteIdAndData(pacienteId, data)).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> validacao.validar(consulta),
                "Nenhuma exceção deve ser lançada.");
    }

    @Test
    void deveLancarUmaExcecaoAoValidarQueOPacienteNaoPossuiUmaConsultaAgendadaParaAqueleDia() {
        Consulta consulta = getEntidadeConsulta();
        when(consultaRepositoryMock.existsByPacienteIdAndData(pacienteId, data)).thenReturn(true);
        Assertions.assertThrows(ConsultaNaoPodeSerMarcadaException.class,
                () -> validacao.validar(consulta),
                "Deve lançar exceçao.");
    }
}