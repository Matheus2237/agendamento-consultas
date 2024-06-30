package br.com.matheus.agendamentoconsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * Repositório genérico para verificação de existência de e-mails em qualquer entidade.
 * </p>
 * <p>
 * Este repositório oferece um método genérico para verificar se um e-mail já está presente em
 * qualquer repositório que herde de JpaRepository.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
public interface EmailRepository {

    /**
     * Verifica se um e-mail já existe em um repositório específico.
     *
     * @param email      O endereço de e-mail a ser verificado.
     * @param repository O repositório específico a ser consultado. Deve herdar de JpaRepository.
     * @return {@code true} se o e-mail existir no repositório, {@code false} caso contrário.
     */
    <T extends JpaRepository<?, ?>> boolean existsByEmail(String email, T repository);
}