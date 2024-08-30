package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoRequestDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.EnderecoRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.matheus.agendamentoconsultas.constraints.validator.UFValidator.isValidUF;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>
 * Validador para o constraint {@link br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoRequestDTO}.
 * </p>
 * <p>
 * Esta classe implementa a lógica de validação para a anotação @ValidEnderecoRequestDTO. Ela verifica
 * se os campos do endereço estão preenchidos e se os valores do UF e do CEP são válidos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Component
public class ValidEnderecoRequestDTOValidator implements ConstraintValidator<ValidEnderecoRequestDTO, EnderecoRequestDTO> {

    private static final Pattern UF_PATTERN = Pattern.compile("^([A-Z]{2})$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");

    /**
     * Verifica se o endereço informado é válido.
     *
     * @param endereco O DTO do endereço que será validado.
     * @param context  O contexto de validação.
     * @return {@code true} se o endereço é válido ou se algum dado está nulo/vazio, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(EnderecoRequestDTO endereco, ConstraintValidatorContext context) {
        return isSomeDataNullOrEmpty(endereco) || isUfECepValidos(endereco.uf(), endereco.cep());
    }

    /**
     * Verifica se algum dado do endereço está nulo ou vazio.
     *
     * @param endereco O DTO do endereço que será verificado.
     * @return {@code true} se algum dado está nulo ou vazio, {@code false} caso contrário.
     */
    private boolean isSomeDataNullOrEmpty(EnderecoRequestDTO endereco) {
        return isNull(endereco)
                || isBlank(endereco.logradouro())
                || isBlank(endereco.numero())
                || isBlank(endereco.bairro())
                || isBlank(endereco.cidade())
                || isBlank(endereco.uf())
                || isBlank(endereco.cep());
    }

    /**
     * Verifica se os valores do UF e do CEP são válidos.
     *
     * @param uf  O estado do endereço que será verificado.
     * @param cep O CEP do endereço que será verificado.
     * @return {@code true} se o UF e o CEP são válidos, {@code false} caso contrário.
     */
    private boolean isUfECepValidos(String uf, String cep) {
        return isValid(uf)
                && CEP_PATTERN.matcher(cep).matches();
    }

    private boolean isValid(String uf) {
        Matcher ufMatcher = UF_PATTERN.matcher(uf);
        if (ufMatcher.matches()) {
            String ufMatch = ufMatcher.group(1);
            return isValidUF(ufMatch);
        }
        return false;
    }
}