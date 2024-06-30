package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Repositório para operações de persistência de {@link br.com.matheus.agendamentoconsultas.model.Paciente}.
 * </p>
 * <p>
 * Este repositório oferece métodos para consultar e manipular dados de pacientes,
 * incluindo verificação de existência por CPF e e-mail.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Verifica se existe um paciente com o CPF especificado.
     *
     * @param cpf O CPF do paciente
     * @return {@code true} se existir um paciente com o CPF especificado, caso contrário {@code false}
     */
    boolean existsByCpfValue(String cpf);

    /**
     * Verifica se existe um paciente com o e-mail especificado.
     *
     * @param email O e-mail do paciente
     * @return {@code true} se existir um paciente com o e-mail especificado, caso contrário {@code false}
     */
    boolean existsByEmailValue(String email);
}