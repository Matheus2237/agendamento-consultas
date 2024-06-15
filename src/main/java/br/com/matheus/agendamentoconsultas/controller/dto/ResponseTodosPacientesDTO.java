package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public record ResponseTodosPacientesDTO(

        Long id,
        String nome,
        String email,
        TelefoneResponseDTO telefone
) {

    public ResponseTodosPacientesDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail().getValue(),
                new TelefoneResponseDTO(paciente.getTelefone()));
    }
}