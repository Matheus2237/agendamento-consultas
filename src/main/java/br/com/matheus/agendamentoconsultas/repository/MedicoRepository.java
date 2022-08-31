package br.com.matheus.agendamentoconsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.agendamentoconsultas.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	boolean existsByEmail(String email);
	boolean existsByCrm(String crm);
}
