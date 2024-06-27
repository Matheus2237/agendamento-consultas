package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Repositório para operações de persistência de {@link br.com.matheus.agendamentoconsultas.model.Medico}.
 * </p>
 * <p>
 * Este repositório oferece métodos para consultar e manipular dados de médicos,
 * incluindo verificação de existência por CRM e e-mail.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	/**
	 * Verifica se existe um médico com o CRM especificado.
	 *
	 * @param crm O CRM do médico
	 * @return {@code true} se existir um médico com o CRM especificado, caso contrário {@code false}
	 */
	boolean existsByCrmValue(String crm);

	/**
	 * Verifica se existe um médico com o e-mail especificado.
	 *
	 * @param email O e-mail do médico
	 * @return {@code true} se existir um médico com o e-mail especificado, caso contrário {@code false}
	 */
	boolean existsByEmailValue(String email);
}