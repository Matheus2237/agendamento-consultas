package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	boolean existsByCrmValue(String crm);

	boolean existsByEmailValue(String email);
}