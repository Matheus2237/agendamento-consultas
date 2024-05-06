package br.com.matheus.agendamentoconsultas.model;

import br.com.matheus.agendamentoconsultas.model.vo.CRM;
import br.com.matheus.agendamentoconsultas.model.vo.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@Embedded
	private CRM crm;

	@Embedded
	private Email email;

	@Embedded
	private Telefone telefone;

//	@Enumerated(value = EnumType.STRING)
//	private Especializacao especializacao;

	@Embedded
	private Endereco endereco;

//	@ElementCollection
//	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
//	@JoinColumn(name = "id_medico")
//	private List<HorarioAtendimento> horariosAtendimento;

	public Medico(String nome, String crm, String email, Telefone telefone, Endereco endereco) {
		this.nome = nome;
		this.crm = new CRM(crm);
		this.email = new Email(email);
		this.telefone = telefone;
		this.endereco = endereco;
	}
}