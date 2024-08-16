package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.base.AbstractDateFixedAndDatabaseProvidedIntegrationTest;
import br.com.matheus.agendamentoconsultas.base.json.HttpBodyJsonSource;
import br.com.matheus.agendamentoconsultas.base.json.HttpUrlParamJsonSource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MedicoControllerIntegrationTest extends AbstractDateFixedAndDatabaseProvidedIntegrationTest {

    private static final String MEDICO_INSERT_SCRIPT = "classpath:sql/medico/insert.sql";

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/medico/visualizar_todos_cadastrado.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar200AoVisualizarTodosOsPacientesCadastradosNoSistema(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/medico/visualizar_todos_vazio.json")
    @SneakyThrows
    void deveRetornar200AoTentarVisualizarTodosOsPacientesQuandoNaoHouverNenhumCadastrado(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/cadastro_sucesso.json")
    @SneakyThrows
    void deveRetornar201AoCadastrarUmMedicoComSucesso(String request, String expectedResponse){
        final String createdURI = "/medico/1";
        mockMvc.perform(
                        post("/medico")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(createdURI)))
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/cadastro_informacoes_faltantes.json")
    @SneakyThrows
    void deveRetornar400AoTentarCadastrarUmMedicoComAlgumDadoFaltanteOuEmBranco(String request, String expectedResponse) {
        mockMvc.perform(
                        post("/medico")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/cadastro_crm_email_repetidos.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar400AoTentarCadastrarUmMedicoComCRMOuEmailJaExistentesNoBancoDeDados(String request, String expectedResponse) {
        mockMvc.perform(
                        post("/medico")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/medico/visualizacao_detalhes_cadastrados.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar200AoVisualizarOsDetalhesDeUmMedicoJaCadastradoNaAplicacao(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/medico/visualizacao_detalhes_nao_cadastrados.json")
    @SneakyThrows
    void deveRetornar404AoSolicitarAVisualizacaoDosDetalhesDeUmMedicoNaoExistenteNoSistema(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound())
                .andExpect(content().string(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/atualizacao_medico_cadastrado.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar200AoTentarAtualizarComDadosValidosUmMedicoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(
                        put("/medico/1")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/atualizacao_medico_dados_invalidos.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar400AoTentarAtualizarComDadosInvalidosUmMedico(String request, String expectedResponse) {
        mockMvc.perform(
                        put("/medico/1")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/atualizacao_medico_nao_cadastrado.json")
    @SneakyThrows
    void deveRetornar404AoTentarAtualizarUmMedicoNaoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(
                        put("/medico/1")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar200AoDeletarUmMedicoPreviamenteCadastradoNoSistema() {
        String url = "/medico/1";
        mockMvc.perform(delete(url))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/medico/deletar_nao_cadastrado.json")
    @SneakyThrows
    void deveRetornar404AoTentarDeletarUmMedicoNaoCadastradoNoSistema(String url, String expectedResponse) {
        mockMvc.perform(delete(url))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/atualizacao_horario_atendimento_cadastrado.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar200AoAtualizarOsHorariosDeAtendimentoDeUmMedicoPreviamenteCadastradoNoSistemaComHorariosValidos(String request, String expectedResponse) {
        mockMvc.perform(put("/medico/1/horarios-atendimento")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/atualizacao_horario_atendimento_dados_invalidos.json")
    @Sql(scripts = MEDICO_INSERT_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar400AoTentarAtualizarOsHorariosAtendimentoDeUmMedicoPreviamenteCadastradoNoSistemaComHorariosInvalidos(String request, String expectedResponse) {
        mockMvc.perform(put("/medico/1/horarios-atendimento")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/medico/atualizacao_horario_atendimento_nao_cadastrado.json")
    @SneakyThrows
    void deveRetornar404AoTentarAtualizarOsHorariosAtendimentoDeUmMedicoNaoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(put("/medico/1/horarios-atendimento")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }
}