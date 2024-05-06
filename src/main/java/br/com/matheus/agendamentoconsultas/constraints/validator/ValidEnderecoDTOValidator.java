package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidEnderecoDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroPacienteDTO.EnderecoDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidEnderecoDTOValidator implements ConstraintValidator<ValidEnderecoDTO, EnderecoDTO> {

	@Override
	public boolean isValid(EnderecoDTO endereco, ConstraintValidatorContext context) {
		// TODO: Implement rule
		return false;
	}
}