package br.com.matheus.agendamentoconsultas.service.consulta.validations;

import br.com.matheus.agendamentoconsultas.model.Consulta;

/**
 * Interface para validação de agendamento de consultas.
 * Implementações desta interface devem realizar validações específicas para garantir que a consulta pode ser agendada.
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public interface ValidacaoAgendamentoConsulta {

    /**
     * Valida se a consulta pode ser agendada.
     *
     * @param consulta a consulta a ser validada
     */
    void validar(Consulta consulta);
}