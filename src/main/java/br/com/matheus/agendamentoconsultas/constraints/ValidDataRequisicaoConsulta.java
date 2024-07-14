package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.ValidDataRequisicaoConsultaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Anotação de validação de data de requisição de consulta.
 * </p>
 * <p>
 * Esta anotação é usada para validar se a data da requisição de consulta é válida.
 * </p>
 *
 * @author  Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = ValidDataRequisicaoConsultaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDataRequisicaoConsulta {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}