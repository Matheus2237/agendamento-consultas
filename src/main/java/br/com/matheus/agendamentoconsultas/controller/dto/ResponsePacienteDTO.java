package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public record ResponsePacienteDTO(

        Long id,
        String nome,
        String cpf,
        String email,
        TelefoneResponseDTO telefone,
        EnderecoResponseDTO endereco
) {

    public ResponsePacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf().getValue(), paciente.getEmail().getValue(),
                new TelefoneResponseDTO(paciente.getTelefone()), new EnderecoResponseDTO(paciente.getEndereco()));
    }
}