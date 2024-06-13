package com.diogo.barbernet.api.domain.ponto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoPontos(
        Long id,
        String nomeCabeleireiro,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime entrada,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime saida
){

        public DadosDetalhamentoPontos(Ponto ponto) {
        this(
                ponto.getId(),
                ponto.getCabeleireiro().getNome(),
                ponto.getEntrada(),
                ponto.getSaida()
        );
    }
}
