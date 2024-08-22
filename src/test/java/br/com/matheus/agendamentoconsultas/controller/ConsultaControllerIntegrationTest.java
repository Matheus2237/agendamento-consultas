package br.com.matheus.agendamentoconsultas.controller;

import br.com.matheus.agendamentoconsultas.base.AbstractDateFixedAndDatabaseProvidedIntegrationTest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @ParameterizedTest
    @HttpBodyJsonSource("json_source/consulta/agendamento_sucesso.json")
    @Sql(scripts = CONSULTA_PERMITIDA_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_PERMITIDA_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_PERMITIDA_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_PACIENTE_NAO_CADASTRADO, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_MEDICO_NAO_CADASTRADO, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_PERMITIDA_SCRIPT, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_PACIENTE_MESMO_DIA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_HORARIO_JA_AGENDADO, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_MEDICO_AGENDA_LOTADA_NO_DIA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_PERMITIDA_MEDICO_DEFINIDO_PELO_SISTEMA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
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
    @Sql(scripts = CONSULTA_INDISPONIVEL_MEDICO_DEFINIDO_PELO_SISTEMA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar404QuandoOPacienteDeixarOSistemaEscolherOMedicoParaConsultaENaoHouverNenhumDisponivel(String request, String expectedResponse) {
        mockMvc.perform(post("/consulta/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @Sql(scripts = CONSULTA_A_SER_REMOVIDA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveCancelarUmaConsultaPreviamenteAgendadaNoSistemaComBaseNoSeuIdEComMaisDeUmDiaDeAntecedencia() {
        mockMvc.perform(delete("/consulta/1"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/cancelamento_consulta_no_mesmo_dia.json")
    @Sql(scripts = CONSULTA_CANCELAMENTO_MESMO_DIA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar403AoTentarCancelarUmaConsultaNoMesmoDiaEmQueEstaAgendada(String url, String expectedResponse) {
        mockMvc.perform(delete(url))
                .andExpect(status().isForbidden())
                .andExpect(content().json(expectedResponse));
    }

    @ParameterizedTest
    @HttpUrlParamJsonSource("json_source/consulta/cancelamento_consulta_nao_encontrada.json")
    @Sql(scripts = CONSULTA_A_SER_REMOVIDA, executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @SneakyThrows
    void deveRetornar404AoTentarCancelarUmaConsultaQueNaoExisteNoSistema(String url, String expectedResponse) {
        mockMvc.perform(delete(url))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse));
    }
}