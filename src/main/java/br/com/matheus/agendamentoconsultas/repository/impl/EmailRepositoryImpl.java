package br.com.matheus.agendamentoconsultas.repository.impl;

import br.com.matheus.agendamentoconsultas.repository.EmailRepository;
import br.com.matheus.agendamentoconsultas.repository.MedicoRepository;
import br.com.matheus.agendamentoconsultas.repository.PacienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Implementação customizada do repositório de e-mails.
 * </p>
 * <p>
 * Esta classe implementa a interface {@link br.com.matheus.agendamentoconsultas.repository.EmailRepository} e define
 * a lógica para verificar a existência de um e-mail em repositórios específicos de {@link PacienteRepository} ou
 * {@link MedicoRepository}.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class EmailRepositoryImpl implements EmailRepository {

    /**
     * Verifica se um determinado e-mail existe em um repositório específico.
     *
     * @param email E-mail a ser verificado.
     * @param repository Repositório de dados onde será realizada a verificação.
     * @return {@code true} se o e-mail existir no repositório especificado, {@code false} caso contrário.
     * @throws IllegalStateException se o tipo de repositório não for suportado pela aplicação.
     */
    @Override
    public <T extends JpaRepository<?, ?>> boolean existsByEmail(String email, T repository) {
        return switch (repository) {
            case PacienteRepository pacienteRepository -> pacienteRepository.existsByEmailValue(email);
            case MedicoRepository medicoRepository -> medicoRepository.existsByEmailValue(email);
            default -> throw new IllegalStateException("Unexpected value: " + repository);
        };
    }
}