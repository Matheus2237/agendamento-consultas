package br.com.matheus.agendamentoconsultas.constraints.validator;

import lombok.NoArgsConstructor;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

/**
 * <p>
 * Classe utilitária para validação de siglas de Unidades Federativas (UF) do Brasil.
 * </p>
 * <p>
 * Esta classe verifica se uma determinada string representa uma sigla de UF válida
 * em conformidade com a lista oficial de UFs brasileiras.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@NoArgsConstructor(access = PRIVATE)
class UFValidator {

    private static final Set<String> UFS;

    static {
        UFS = Set.of(
                "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        );
    }

    /**
     * Verifica se a string fornecida é uma sigla de UF válida.
     *
     * @param uf A sigla de UF a ser verificada.
     * @return true se a sigla for uma UF válida; false caso contrário.
     * @throws NullPointerException se o parâmetro uf for null.
     */
    public static boolean isValidUF(String uf) {
        requireNonNull(uf, "A sigla da UF não pode ser null");
        return UFS.contains(uf);
    }
}