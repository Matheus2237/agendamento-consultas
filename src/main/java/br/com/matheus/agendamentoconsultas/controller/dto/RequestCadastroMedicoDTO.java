package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.constraints.*;
import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Medico;
import br.com.matheus.agendamentoconsultas.model.Telefone;
import br.com.matheus.agendamentoconsultas.model.enums.DiaDaSemana;
import br.com.matheus.agendamentoconsultas.model.enums.Especializacao;
import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Set;

/**
 * DTO para solicitação de cadastro de médico.
 *
 * @param nome                O nome do médico. Não pode ser vazio ou nulo.
 * @param crm                 O CRM do médico. Não pode ser vazio ou nulo.
 * @param email               O email do médico. Não pode ser vazio ou nulo.
 * @param telefone            O telefone do médico. Não pode ser nulo.
 * @param endereco            O endereço do médico. Não pode ser nulo.
 * @param especializacao      A especialização do médico. Não pode ser vazio ou nulo.
 * @param horariosAtendimento Os horários de atendimento do médico. Não pode ser nulo.
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Schema(description = "DTO para cadastro de médico")
public record RequestCadastroMedicoDTO(

        @NotBlank(message = "O campo 'nome' é obrigatório")
        @Schema(description = "Nome do médico", example = "Dr. João Silva")
        String nome,

        @NotBlank(message = "O campo 'crm' é obrigatório")
        @UniqueCrm
        @ValidCrm
        @Schema(description = "CRM do médico", example = "MG123456")
        String crm,

        @NotBlank(message = "O campo 'email' é obrigatório")
        @jakarta.validation.constraints.Email
        @UniqueEmail(repository = MedicoRepository.class)
        @Schema(description = "Email do médico", example = "joao.silva@exemplo.com")
        String email,

        @Valid
        @NotNull(message = "O campo 'telefone' é obrigatório")
        @ValidTelefoneRequestDTO
        @Schema(description = "Telefone do médico")
        TelefoneRequestDTO telefone,

        @Valid
        @NotNull(message = "O campo 'endereco' é obrigatório")
        @ValidEnderecoRequestDTO
        @Schema(description = "Endereço do médico")
        EnderecoRequestDTO endereco,

        @ValidEspecializacao
        @NotBlank(message = "O campo 'especialização' é obrigatório")
        @Schema(description = "Especialização do médico", example = "CARDIOLOGIA")
        String especializacao,

        @Valid
        @NotNull(message = "O campo 'horariosAtendimento' é obrigatório")
        @Schema(description = "Horários de atendimento do médico")
        Set<HorarioAtendimentoRequestDTO> horariosAtendimento
) {
        public Medico toEntity() {

                Telefone telefoneEntity = telefone.toEntity();
                Endereco enderecoEntity = endereco.toEntity();
                CRM crmEntity = new CRM(crm);
                Email emailEntity = new Email(email);
                Especializacao especializacaoEntity = Especializacao.valueOf(especializacao);

                Medico medico = new Medico(nome, crmEntity, emailEntity, telefoneEntity,
                        enderecoEntity, especializacaoEntity);

                horariosAtendimento.forEach(hrAt -> {
                        DiaDaSemana diaDaSemana = DiaDaSemana.valueOf(hrAt.diaDaSemana());
                        LocalTime horaInicial = LocalTime.parse(hrAt.horaInicial());
                        LocalTime horaFinal = LocalTime.parse(hrAt.horaFinal());
                        medico.adicionaHorarioAtendimento(diaDaSemana, horaInicial, horaFinal);
                });

                return medico;
        }
}