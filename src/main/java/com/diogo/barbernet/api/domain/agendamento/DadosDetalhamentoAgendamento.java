package com.diogo.barbernet.api.domain.agendamento;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(Long id, Long idCabeleireiro, Long idCliente, LocalDateTime data) {

    public DadosDetalhamentoAgendamento(Agendamento agendamento){
        this(   agendamento.getId(),
                agendamento.getCabeleireiro().getId(),
                agendamento.getCliente().getId(),
                agendamento.getDataHora() );
    }
}
