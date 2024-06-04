package com.diogo.barbernet.api.domain.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
                convertToBrasilia(agendamento.getDataHora()),
                agendamento.getPrecoEstimado(),
                agendamento.getStatus(),
                agendamento.getMetodoPagamento()
        );
    }

    private static LocalDateTime convertToBrasilia(LocalDateTime utcDateTime) {
        ZoneId brasiliaZoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime brasiliaDateTime = utcDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(brasiliaZoneId);
        return brasiliaDateTime.toLocalDateTime();
    }
}
