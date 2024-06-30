package br.com.matheus.agendamentoconsultas.exception.handler;

import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.FailedRequestValidationDTO;
import io.swagger.v3.oas.annotations.Hidden;
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

/**
 * <p>
 * ApplicationExceptionHandler é responsável por tratar exceções lançadas pela aplicação.
 * </p>
 *
 * <p>
 * Este handler captura exceções específicas e retorna respostas apropriadas para o cliente.
 * </p>
 *
 * <p>
 * As exceções tratadas incluem:
 * <ul>
 *   <li>{@link org.springframework.web.bind.MethodArgumentNotValidException}</li>
 *   <li>{@link br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException}</li>
 *   <li>{@link br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Esta classe utiliza {@link MessageSource} para internacionalização de mensagens de erro.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

	private final MessageSource messageSource;

	/**
	 * Construtor para criar uma instância de {@code ApplicationExceptionHandler}.
	 *
	 * @param messageSource A fonte de mensagens para internacionalização.
	 */
	@Autowired
    public ApplicationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

	/**
	 * Handler para {@link org.springframework.web.bind.MethodArgumentNotValidException}.
	 *
	 * @param exception A exceção lançada quando argumentos de método não são válidos.
	 * @return Uma lista de mensagens de erro de validação.
	 */
	@Hidden
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handle(MethodArgumentNotValidException exception) {
		List<String> listaDeErros = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			FailedRequestValidationDTO erro = new FailedRequestValidationDTO(error.getField(), mensagem);
			listaDeErros.add(erro.toString());
		});
		return listaDeErros;
	}

	/**
	 * Handler para {@link br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException}.
	 *
	 * @param exception A exceção lançada quando um médico não é encontrado.
	 * @return A mensagem de erro da exceção.
	 */
	@Hidden
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(MedicoNaoEncontradoException.class)
	public String handle(MedicoNaoEncontradoException exception) {
		return exception.getMessage();
	}

	/**
	 * Handler para {@link br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException}.
	 *
	 * @param exception A exceção lançada quando um paciente não é encontrado.
	 * @return A mensagem de erro da exceção.
	 */
	@Hidden
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PacienteNaoEncontradoException.class)
	public String handle(PacienteNaoEncontradoException exception) {
		return exception.getMessage();
	}
}