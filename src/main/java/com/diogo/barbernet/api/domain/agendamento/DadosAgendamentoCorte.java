package com.diogo.barbernet.api.domain.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosAgendamentoCorte(
        Long id,
        Long cabeleireiro,
        Long cliente,
        String nomeCabeleireiro,
        String nomeCliente,
        @Future(message = "A data deve estar no futuro")
        LocalDateTime data,
        @Positive(message = "O preço estimado deve ser positivo")
        BigDecimal precoEstimado,
        StatusAgendamento statusAgendamento,
        MetodoPagamento metodoPagamento,
        String observacao
) {}
