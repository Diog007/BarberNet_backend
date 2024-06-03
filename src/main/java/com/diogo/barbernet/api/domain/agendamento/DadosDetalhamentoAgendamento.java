package com.diogo.barbernet.api.domain.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DadosDetalhamentoAgendamento(Long id, String nomeCabeleireiro, String nomeCliente, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime data) {

    public DadosDetalhamentoAgendamento(Agendamento agendamento){
        this(   agendamento.getId(),
                agendamento.getCabeleireiro().getNome(),
                agendamento.getCliente().getNome(),
                agendamento.getDataHora());
    }
}
