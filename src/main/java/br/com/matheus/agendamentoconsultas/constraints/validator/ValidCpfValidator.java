package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidCpfValidator implements ConstraintValidator<ValidCpf, String> {

	// TODO: Incluir logica de validacao na classe CPF
    @Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
        final String regex = "^(\\d{11})$";
        return Pattern.compile(regex).matcher(cpf).matches();
	}
}