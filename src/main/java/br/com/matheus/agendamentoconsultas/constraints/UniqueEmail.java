package br.com.matheus.agendamentoconsultas.constraints;

import br.com.matheus.agendamentoconsultas.constraints.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Anotação de validação para Email único.
 * </p>
 * <p>
 * Esta anotação é usada para validar se o valor do campo anotado é um email único no sistema.
 * Permite especificar o repositório JPA a ser utilizado para a verificação.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

	String message() default "Email já registrado.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends JpaRepository<?, ?>> repository();
}