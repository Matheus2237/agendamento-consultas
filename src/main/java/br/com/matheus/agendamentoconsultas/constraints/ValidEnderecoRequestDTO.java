package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidEnderecoRequestDTOValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Anotação de validação de DTO de endereço.
 * </p>
 * <p>
 * Esta anotação é usada para validar se os campos obrigatórios do DTO de endereço estão preenchidos
 * corretamente.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidEnderecoRequestDTOValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnderecoRequestDTO {

    String message() default "Endereço inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}