package br.com.matheus.agendamentoconsultas.exception.handler;

import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoEncontradaException;
import br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException;
import br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.PacienteNaoEncontradoException;
import br.com.matheus.agendamentoconsultas.exception.handler.dto.ConsultaNaoAgendadaDTO;
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

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FailedRequestValidationDTO> handle(MethodArgumentNotValidException exception) {
        List<FieldError> camposInvalidos = exception.getBindingResult().getFieldErrors();
        return camposInvalidos.stream()
                .map(this::getMensagemDeErroDeValidacaoDeCampoFormatada)
                .toList();
    }

    /**
     * Cria um DTO de erro de validação a partir de um {@link FieldError} em um campo específco.
     *
     * @param campoInvalido O campo que está com erro.
     * @return Um DTO da mensagem de erro de validação.
     */
    private FailedRequestValidationDTO getMensagemDeErroDeValidacaoDeCampoFormatada(FieldError campoInvalido) {
        String mensagem = messageSource.getMessage(campoInvalido, LocaleContextHolder.getLocale());
        return new FailedRequestValidationDTO(campoInvalido.getField(), mensagem);
    }

    /**
     * Handler para {@link br.com.matheus.agendamentoconsultas.exception.MedicoNaoEncontradoException}.
     *
     * @param exception A exceção lançada quando um médico não é encontrado.
     * @return A mensagem de erro da exceção.
     */
    @Hidden
    @ResponseStatus(NOT_FOUND)
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
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PacienteNaoEncontradoException.class)
    public String handle(PacienteNaoEncontradoException exception) {
        return exception.getMessage();
    }

    /**
     * Handler para {@link br.com.matheus.agendamentoconsultas.exception.ConsultaNaoEncontradaException}.
     *
     * @param exception A exceção lançada quando um paciente não é encontrado.
     * @return A mensagem de erro da exceção.
     */
    @Hidden
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ConsultaNaoEncontradaException.class)
    public String handle(ConsultaNaoEncontradaException exception) {
        return exception.getMessage();
    }

    /**
     * Handler para {@link br.com.matheus.agendamentoconsultas.exception.ConsultaNaoPodeSerMarcadaException}.
     *
     * @param exception A exceção lançada quando uma consulta não pode ser agendada.
     * @return Um {@link ConsultaNaoAgendadaDTO} com o motivo da consulta não agendada.
     */
    @Hidden
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConsultaNaoPodeSerMarcadaException.class)
    public ConsultaNaoAgendadaDTO handle(ConsultaNaoPodeSerMarcadaException exception) {
        return new ConsultaNaoAgendadaDTO(exception.getMessage());
    }
}