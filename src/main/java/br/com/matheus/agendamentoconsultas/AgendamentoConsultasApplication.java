package br.com.matheus.agendamentoconsultas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Classe principal responsável por iniciar a aplicação de Agendamento de Consultas.
 * </p>
 * <p>
 * Esta API RESTful gerencia consultas médicas, oferecendo operações de CRUD para pacientes, médicos e agendamentos.
 * </p>
 *
 * @author Matheus Paulino Ribeiro
 * @since 1.0.0
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Agendamento Consultas",
        version = "1.0.0",
        description = "API para agendamento de consultas médicas"))
public class AgendamentoConsultasApplication {

    public static void main(String... args) {
        SpringApplication.run(AgendamentoConsultasApplication.class, args);
    }
}