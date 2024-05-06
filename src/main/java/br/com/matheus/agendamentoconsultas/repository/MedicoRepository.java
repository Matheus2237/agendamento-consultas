package br.com.matheus.agendamentoconsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.agendamentoconsultas.model.Medico;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	boolean existsByEmail(String email);
	boolean existsByCrm(String crm);
}