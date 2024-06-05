package com.diogo.barbernet.api.domain.agendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosAtualizarCorte(
        Long id,
        Long cabeleireiro,
        Long cliente,
        LocalDateTime data,
        BigDecimal precoEstimado,
        StatusAgendamento statusAgendamento,
        MetodoPagamento metodoPagamento ) {

}
