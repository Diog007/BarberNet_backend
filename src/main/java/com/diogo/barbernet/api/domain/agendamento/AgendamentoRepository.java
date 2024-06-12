package com.diogo.barbernet.api.domain.agendamento;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByClienteIdAndDataHoraBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByCabeleireiroIdAndDataHoraBetweenAndStatusNotIn(Long cabeleireiro, LocalDateTime inicioIntervalo, LocalDateTime fimIntervalo, List<StatusAgendamento> statusExcluidos);

    List<Agendamento> findAllByCabeleireiroId(Long id);
}
