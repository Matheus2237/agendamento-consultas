package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.vo.CPF;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@Embedded
	private CPF cpf;

	@Embedded
	private Email email;

	@Embedded
	private Telefone telefone;

	@Embedded
	private Endereco endereco;

	public Paciente(String nome, String cpf, String email, Telefone telefone, Endereco endereco) {
		this.nome = nome;
		this.cpf = new CPF(cpf);
		this.email = new Email(email);
		this.telefone = telefone;
		this.endereco = endereco;
	}
}