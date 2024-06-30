package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Medico;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO para resposta de médico.
 *
 * @param id                  O ID do médico.
 * @param nome                O nome do médico.
 * @param crm                 O CRM do médico.
 * @param email               O email do médico.
 * @param telefone            O telefone do médico.
 * @param endereco            O endereço do médico.
 * @param especializacao      A especialização do médico.
 * @param horariosAtendimento Os horários de atendimento do médico.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para resposta de médico")
public record ResponseMedicoDTO(

        @Schema(description = "ID do médico", example = "1")
        Long id,

        @Schema(description = "Nome do médico", example = "Dr. João Silva")
        String nome,

        @Schema(description = "CRM do médico", example = "MG123456")
        String crm,

        @Schema(description = "Email do médico", example = "joao.silva@exemplo.com")
        String email,

        @Schema(description = "Telefone do médico")
        TelefoneResponseDTO telefone,

        @Schema(description = "Endereço do médico")
        EnderecoResponseDTO endereco,

        @Schema(description = "Especialização do médico", example = "CARDIOLOGIA")
        String especializacao,

        @Schema(description = "Horários de atendimento do médico")
        Set<HorarioAtendimentoResponseDTO> horariosAtendimento
) {

    /**
     * Construtor que cria um DTO de resposta a partir de uma entidade de médico.
     *
     * @param medico A entidade de médico.
     */
    public ResponseMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm().getValue(), medico.getEmail().getValue(),
                new TelefoneResponseDTO(medico.getTelefone()), new EnderecoResponseDTO(medico.getEndereco()),
                medico.getEspecializacao().toString(), medico.getHorariosAtendimento().stream()
                        .map(HorarioAtendimentoResponseDTO::new).collect(Collectors.toSet()));
    }
}