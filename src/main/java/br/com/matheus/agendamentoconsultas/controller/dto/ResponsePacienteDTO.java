package br.com.matheus.agendamentoconsultas.controller.dto;

import br.com.matheus.agendamentoconsultas.model.Endereco;
import br.com.matheus.agendamentoconsultas.model.Paciente;
import br.com.matheus.agendamentoconsultas.model.Telefone;

public record ResponsePacienteDTO(

        Long id,
        String nome,
        String cpf,
        String email,
        TelefoneResponseDTO telefone,
        EnderecoResponseDTO endereco
) {

    private record TelefoneResponseDTO(String ddd, String numero) {

        public TelefoneResponseDTO(Telefone telefone) {
            this(telefone.getDdd(), telefone.getNumero());
        }
    }

    private record EnderecoResponseDTO(
            String logradouro, String numero, String bairro, String cidade, String uf, String cep) {

        public EnderecoResponseDTO(Endereco endereco) {
            this(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(),
                    endereco.getCidade(), endereco.getUf(), endereco.getCep());
        }
    }

    public ResponsePacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf().getValue(), paciente.getEmail().getValue(),
                new TelefoneResponseDTO(paciente.getTelefone()), new EnderecoResponseDTO(paciente.getEndereco()));
    }
}