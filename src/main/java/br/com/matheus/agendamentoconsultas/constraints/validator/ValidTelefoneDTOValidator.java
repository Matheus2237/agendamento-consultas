package br.com.matheus.agendamentoconsultas.constraints.validator;

import br.com.matheus.agendamentoconsultas.constraints.ValidTelefoneDTO;
import br.com.matheus.agendamentoconsultas.controller.dto.RequestCadastroPacienteDTO.TelefoneDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidTelefoneDTOValidator implements ConstraintValidator<ValidTelefoneDTO, TelefoneDTO> {

	@Override
	public boolean isValid(TelefoneDTO telefone, ConstraintValidatorContext context) {
		// TODO: Implement rule
		return false;
	}
}