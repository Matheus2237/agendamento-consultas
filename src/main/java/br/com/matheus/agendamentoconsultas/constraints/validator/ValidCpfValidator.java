package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidCpfValidator implements ConstraintValidator<ValidCpf, String> {

    private static final Pattern CPF_PATTERN  = Pattern.compile("^(\\d{11})$");

    @Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
        return cpf == null || cpf.trim().isEmpty() || CPF_PATTERN.matcher(cpf).matches();
	}
}