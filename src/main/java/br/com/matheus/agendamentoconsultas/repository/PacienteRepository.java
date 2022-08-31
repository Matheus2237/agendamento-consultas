package br.com.matheus.agendamentoconsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.agendamentoconsultas.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
}
