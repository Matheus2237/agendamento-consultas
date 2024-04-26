package br.com.matheus.agendamentoconsultas.exception.handler.dto;

import lombok.Getter;

@Getter
public class RequestValidationHandledExceptionDto {

	private final String campo;
	private final String mensagem;
	
	public RequestValidationHandledExceptionDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String toString() {
		return "Erro no campo " + this.campo + " -> " + this.mensagem;
	}
}
