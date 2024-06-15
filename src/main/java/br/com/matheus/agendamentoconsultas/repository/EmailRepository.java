package br.com.matheus.agendamentoconsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository {

    <T extends JpaRepository<?, ?>> boolean existsByEmail(String email, T repository);
}