package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.base.AbstractDateFixedAndDatabaseProvidedIntegrationTest;
import br.com.matheus.agendamentoconsultas.base.db.SqlScriptToExecuteBeforeTestMethod;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PacienteControllerIntegrationTest extends AbstractDateFixedAndDatabaseProvidedIntegrationTest {

    private static final String PACIENTE_INSERT_SCRIPT = "classpath:sql/paciente/insert.sql";

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/paciente/visualizar_todos_cadastrado.json")
    @SqlScriptToExecuteBeforeTestMethod(PACIENTE_INSERT_SCRIPT)
    @SneakyThrows
    void deveRetornar200AoVisualizarTodosOsPacientesCadastradosNoSistema(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/paciente/visualizar_todos_vazio.json")
    @SneakyThrows
    void deveRetornar200AoTentarVisualizarTodosOsPacientesQuandoNaoHouverNenhumCadastrado(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/cadastro_sucesso.json")
    @SneakyThrows
    void deveRetornar201AoCadastrarUmPacienteComSucesso(String request, String expectedResponse){
        final String createdURI = "/paciente/1";
        mockMvc.perform(
                post("/paciente")
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(createdURI)))
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/cadastro_informacoes_faltantes.json")
    @SneakyThrows
    void deveRetornar400AoTentarCadastrarUmPacienteComAlgumDadoFaltanteOuEmBranco(String request, String expectedResponse) {
        mockMvc.perform(
                post("/paciente")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/cadastro_cpf_email_repetidos.json")
    @SqlScriptToExecuteBeforeTestMethod(PACIENTE_INSERT_SCRIPT)
    @SneakyThrows
    void deveRetornar400AoTentarCadastrarUmPacienteComCPFOuEmailJaExistentesNoBancoDeDados(String request, String expectedResponse) {
        mockMvc.perform(
                post("/paciente")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/paciente/visualizacao_detalhes_cadastrados.json")
    @SqlScriptToExecuteBeforeTestMethod(PACIENTE_INSERT_SCRIPT)
    @SneakyThrows
    void deveRetornar200AoVisualizarOsDetalhesDeUmPacienteJaCadastradoNaAplicacao(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/paciente/visualizacao_detalhes_nao_cadastrados.json")
    @SneakyThrows
    void deveRetornar404AoSolicitarAVisualizacaoDosDetalhesDeUmPacienteNaoExistenteNoSistema(String url, String expectedResponse) {
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound())
                .andExpect(content().string(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/atualizacao_cadastrado.json")
    @SqlScriptToExecuteBeforeTestMethod(PACIENTE_INSERT_SCRIPT)
    @SneakyThrows
    void deveRetornar200AoTentarAtualizarComDadosValidosUmPacienteCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(
                put("/paciente/1")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/atualizacao_dados_invalidos.json")
    @SqlScriptToExecuteBeforeTestMethod(PACIENTE_INSERT_SCRIPT)
    @SneakyThrows
    void deveRetornar400AoTentarAtualizarComDadosInvalidosUmPaciente(String request, String expectedResponse) {
        mockMvc.perform(
                put("/paciente/1")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/paciente/atualizacao_nao_cadastrado.json")
    @SneakyThrows
    void deveRetornar404AoTentarAtualizarUmPacienteNaoCadastradoNoSistema(String request, String expectedResponse) {
        mockMvc.perform(
                put("/paciente/1")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @SqlScriptToExecuteBeforeTestMethod(PACIENTE_INSERT_SCRIPT)
    @SneakyThrows
    void deveRetornar200AoDeletarUmPacientePreviamenteCadastradoNoSistema() {
        String url = "/paciente/1";
        mockMvc.perform(delete(url))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/paciente/deletar_nao_cadastrado.json")
    @SneakyThrows
    void deveRetornar404AoTentarDeletarUmPacienteNaoCadastradoNoSistema(String url, String expectedResponse) {
        mockMvc.perform(delete(url))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }
}