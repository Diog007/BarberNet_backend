package com.diogo.barbernet.api.domain.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoCorte(
        Long idCabeleireiro,
        @NotNull
        Long idCliente,

        @NotNull
        @Future
        LocalDateTime data ) {
}
