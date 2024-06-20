package br.com.matheus.agendamentoconsultas.exception.handler;

import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.RequestValidationHandledExceptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	private final MessageSource messageSource;

	@Autowired
    public ApplicationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handle(MethodArgumentNotValidException exception) {
		List<String> listaDeErros = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			RequestValidationHandledExceptionDto erro = new RequestValidationHandledExceptionDto(error.getField(), mensagem);
			listaDeErros.add(erro.toString());
		});
		return listaDeErros;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(MedicoNaoEncontradoException.class)
	public String handle(MedicoNaoEncontradoException exception) {
		return exception.getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PacienteNaoEncontradoException.class)
	public String handle(PacienteNaoEncontradoException exception) {
		return exception.getMessage();
	}
}