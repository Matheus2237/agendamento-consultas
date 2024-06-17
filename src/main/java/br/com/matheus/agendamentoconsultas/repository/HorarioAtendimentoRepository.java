package br.com.matheus.agendamentoconsultas.repository;

import br.com.matheus.agendamentoconsultas.model.HorarioAtendimento;
import br.com.matheus.agendamentoconsultas.model.pk.HorarioAtendimentoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HorarioAtendimentoRepository extends JpaRepository<HorarioAtendimento, HorarioAtendimentoPK> {

    void deleteByMedicoId(Long id);
}