package com.diogo.barbernet.api.domain.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
        Long id,
        String nomeCabeleireiro,
        String nomeCliente,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate criacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime data,
        BigDecimal precoEstimado,
        StatusAgendamento statusAgendamento,
        MetodoPagamento metodoPagamento
) {

    public DadosDetalhamentoAgendamento(Agendamento agendamento){
        this(
                agendamento.getId(),
                agendamento.getCabeleireiro().getNome(),
                agendamento.getCliente().getNome(),
                agendamento.getDataCriacao(),
                agendamento.getDataHora(),
                agendamento.getPrecoEstimado(),
                agendamento.getStatus(),
                agendamento.getMetodoPagamento()
        );
    }
}
