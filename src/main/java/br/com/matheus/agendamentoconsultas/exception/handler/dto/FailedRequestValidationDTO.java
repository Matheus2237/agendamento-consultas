package br.com.matheus.agendamentoconsultas.exception.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * <p>
 * DTO para representar erros de validação de requisição.
 * </p>
 * <p>
 * Este DTO contém o campo que falhou na validação e a mensagem de erro associada.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@Getter
@Schema(description = "DTO para representar erros de validação de requisição")
public class FailedRequestValidationDTO {

	@Schema(description = "O campo que falhou na validação", example = "nome")
	private final String campo;

	@Schema(description = "A mensagem de erro associada à falha de validação", example = "O campo 'nome' é obrigatório")
	private final String mensagem;
	
	public FailedRequestValidationDTO(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	/**
	 * Retorna uma representação em string do erro de validação formatada com o campo e a
	 * mensagem da falha de validação.
	 *
	 * @return Uma string representando o erro de validação.
	 */
	public String toString() {
		return "Erro no campo " + this.campo + " -> " + this.mensagem;
	}
}