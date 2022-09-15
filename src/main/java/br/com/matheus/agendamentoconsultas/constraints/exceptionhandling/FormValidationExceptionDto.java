package br.com.matheus.agendamentoconsultas.constraints.exceptionhandling;

public class FormValidationExceptionDto {

	private String campo;
	private String mensagem;
	
	public FormValidationExceptionDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}
	public String getMensagem() {
		return mensagem;
	}
}
