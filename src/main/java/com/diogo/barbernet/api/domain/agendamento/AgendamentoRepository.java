package com.diogo.barbernet.api.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByCabeleireiroIdAndDataHora(Long idCabeleireiro, LocalDateTime data);

    boolean existsByClienteIdAndDataHoraBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
