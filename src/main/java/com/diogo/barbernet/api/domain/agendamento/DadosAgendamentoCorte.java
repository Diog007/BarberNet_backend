package com.diogo.barbernet.api.domain.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosAgendamentoCorte(
        Long id,
        Long cabeleireiro,
        Long cliente,
        String nomeCabeleireiro,
        String nomeCliente,
        LocalDateTime data,
        BigDecimal precoEstimado,
        StatusAgendamento statusAgendamento,
        MetodoPagamento metodoPagamento,
        String observacao) {

}
