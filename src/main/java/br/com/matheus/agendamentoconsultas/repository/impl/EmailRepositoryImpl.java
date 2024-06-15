package br.com.matheus.agendamentoconsultas.repository.impl;

import br.com.matheus.agendamentoconsultas.repository.EmailRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailRepositoryImpl implements EmailRepository {

    @Override
    public <T extends JpaRepository<?, ?>> boolean existsByEmail(String email, T repository) {
        return switch (repository) {
            case PacienteRepository pacienteRepository -> pacienteRepository.existsByEmailValue(email);
            case MedicoRepository medicoRepository -> medicoRepository.existsByEmailValue(email);
            default -> throw new IllegalStateException("Unexpected value: " + repository);
        };
    }
}