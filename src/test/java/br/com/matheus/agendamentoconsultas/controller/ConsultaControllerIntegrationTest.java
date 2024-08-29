package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.base.AbstractDateFixedAndDatabaseProvidedIntegrationTest;
import br.com.matheus.agendamentoconsultas.base.db.SqlScriptToExecuteBeforeTestMethod;
import br.com.matheus.agendamentoconsultas.base.json.HttpBodyJsonSource;
import br.com.matheus.agendamentoconsultas.base.json.HttpUrlParamJsonSource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConsultaControllerIntegrationTest extends AbstractDateFixedAndDatabaseProvidedIntegrationTest {

    private static final String CONSULTA_PERMITIDA_SCRIPT = "classpath:sql/consulta/consulta_permitida.sql";
    private static final String CONSULTA_PACIENTE_NAO_CADASTRADO = "classpath:sql/consulta/consulta_paciente_nao_cadastrado.sql";
    private static final String CONSULTA_MEDICO_NAO_CADASTRADO = "classpath:sql/consulta/consulta_medico_nao_cadastrado.sql";
    private static final String CONSULTA_PACIENTE_MESMO_DIA = "classpath:sql/consulta/consulta_paciente_mesmo_dia.sql";
    private static final String CONSULTA_HORARIO_JA_AGENDADO = "classpath:sql/consulta/consulta_horario_ja_marcado.sql";
    private static final String CONSULTA_MEDICO_AGENDA_LOTADA_NO_DIA = "classpath:sql/consulta/consulta_medico_agenda_lotada_no_dia.sql";
    private static final String CONSULTA_PERMITIDA_MEDICO_DEFINIDO_PELO_SISTEMA = "classpath:sql/consulta/consulta_permitida_medico_definido_pelo_sistema.sql";
    private static final String CONSULTA_INDISPONIVEL_MEDICO_DEFINIDO_PELO_SISTEMA = "classpath:sql/consulta/consulta_indisponivel_medico_definido_pelo_sistema.sql";
    private static final String CONSULTA_A_SER_REMOVIDA = "classpath:sql/consulta/consulta_a_ser_cancelada.sql";
    private static final String CONSULTA_CANCELAMENTO_MESMO_DIA = "classpath:sql/consulta/consulta_a_ser_removida_no_mesmo_dia.sql";
    private static final String CONSULTAS_NO_DIA = "classpath:sql/consulta/consultas_para_relatorio_no_dia.sql";
    private static final String CONSULTAS_QUE_NAO_SAO_NO_DIA_ESPECIFICADO = "classpath:sql/consulta/consultas_para_relatorio_que_nao_sao_no_dia_especificado.sql";
    private static final String CONSULTAS_NO_MES = "classpath:sql/consulta/consultas_para_relatorio_no_mes.sql";
    private static final String CONSULTAS_QUE_NAO_SAO_NO_MES_ESPECIFICADO = "classpath:sql/consulta/consultas_para_relatorio_que_nao_sao_no_mes_especificado.sql";

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_sucesso.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PERMITIDA_SCRIPT)
    @SneakyThrows
    void deveAgendarUmaConsultaComSucessoQuandoAMesmaPossuiDadosValidos(String request, String expectedResponse) {
        String createdURI = "/consulta/1";
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(createdURI)))
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_dados_faltantes.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PERMITIDA_SCRIPT)
    @SneakyThrows
    void deveRetornar400AoTentarAgendarUmaConsultaComInformacoesFaltantes(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }


    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_dados_invalidos.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PERMITIDA_SCRIPT)
    @SneakyThrows
    void deveRetornar400AoTentarAgendarUmaConsultaComDadosInvalidos(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_paciente_nao_cadastrado.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PACIENTE_NAO_CADASTRADO)
    @SneakyThrows
    void deveRetornar404AoTentarAgendarUmaConsultaComUmPacienteNaoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_medico_nao_cadastrado.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_MEDICO_NAO_CADASTRADO)
    @SneakyThrows
    void deveRetornar404AoTentarAgendarUmaConsultaComUmMedicoNaoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_paciente_e_medico_nao_cadastrado.json")
    @SneakyThrows
    void deveRetornar404AoTentarAgendarUmaConsultaComUmMedicoEUmPacienteNaoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_fora_de_horario_de_atendimento.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PERMITIDA_SCRIPT)
    @SneakyThrows
    void deveRetornar422AoTentarAgendarUmaConsultaForaDoHorarioDeAtendimentoDoMedico(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_paciente_possui_consulta_mesmo_dia.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PACIENTE_MESMO_DIA)
    @SneakyThrows
    void deveRetornar422AoTentarAgendarUmaConsultaQuandoOPacienteJaPossuiAgendadaParaOMesmoDia(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_horario_ja_marcado.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_HORARIO_JA_AGENDADO)
    @SneakyThrows
    void deveRetornar422AoTentarAgendarUmaConsultaEmUmHorarioJaAgendadoComOMesmoMedico(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_medico_agenda_lotada_no_dia.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_MEDICO_AGENDA_LOTADA_NO_DIA)
    @SneakyThrows
    void deveRetornar422AoTentarAgendarUmaConsultaComUmMedicoQueJaTem12ConsultasAgendadasNesseDia(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_sucesso_medico_definido_pelo_sistema.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_PERMITIDA_MEDICO_DEFINIDO_PELO_SISTEMA)
    @SneakyThrows
    void deveAgendarUmaConsultaComSucessoQuandoOPacienteDeixarOSistemaEscolherOMedicoEHouverUmDisponivel(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_consulta_indisponivel_medico_definido_pelo_sistema.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_INDISPONIVEL_MEDICO_DEFINIDO_PELO_SISTEMA)
    @SneakyThrows
    void deveRetornar404QuandoOPacienteDeixarOSistemaEscolherOMedicoParaConsultaENaoHouverNenhumDisponivel(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_A_SER_REMOVIDA)
    @SneakyThrows
    void deveCancelarUmaConsultaPreviamenteAgendadaNoSistemaComBaseNoSeuIdEComMaisDeUmDiaDeAntecedencia() {
        mockMvc.perform(delete("/consulta/1"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/cancelamento_consulta_no_mesmo_dia.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_CANCELAMENTO_MESMO_DIA)
    @SneakyThrows
    void deveRetornar403AoTentarCancelarUmaConsultaNoMesmoDiaEmQueEstaAgendada(String url, String expectedResponse) {
        mockMvc.perform(delete(url))
                .andExpect(status().isForbidden())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/cancelamento_consulta_nao_encontrada.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTA_A_SER_REMOVIDA)
    @SneakyThrows
    void deveRetornar404AoTentarCancelarUmaConsultaQueNaoExisteNoSistema(String url, String expectedResponse) {
        mockMvc.perform(delete(url))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_dia.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_NO_DIA)
    @SneakyThrows
    void deveExibirORelatorioDeConsultaPorDiaComConsultasArmazenadasNoBanco(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_que_nao_sao_no_dia_especificado.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_QUE_NAO_SAO_NO_DIA_ESPECIFICADO)
    @SneakyThrows
    void deveExibirORelatorioDeConsultaPorDiaSemNenhumaConsultaNaqueleDeterminadoDia(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }


    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_dia_parametro_faltante.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_NO_DIA)
    @SneakyThrows
    void deveRetornar400AoRealizarAConsultaDeRelatorioDoDiaComParametrosFaltantes(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_dia_formato_invalido.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_NO_DIA)
    @SneakyThrows
    void deveRetornar400AoRealizarAConsultaDeRelatorioDoDiaComParametrosInvalidos(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_mes.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_NO_MES)
    @SneakyThrows
    void deveExibirORelatorioDeConsultaPorMesComConsultasArmazenadasNoBanco(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_que_nao_sao_no_mes_especificado.json")
    @Sql(scripts = CONSULTAS_QUE_NAO_SAO_NO_MES_ESPECIFICADO, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveExibirORelatorioDeConsultaPorMesSemNenhumaConsultaNaqueleDeterminadoMes(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_mes_parametros_faltantes.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_NO_MES)
    @SneakyThrows
    void deveRetornar400AoRealizarAConsultaDeRelatorioDoMesComParametrosFaltantes(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/relatorio_consultas_mes_formato_invalido.json")
    @SqlScriptToExecuteBeforeTestMethod(CONSULTAS_NO_MES)
    @SneakyThrows
    void deveRetornar400AoRealizarAConsultaDeRelatorioDoMesComParametrosInvalidos(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }
}