package com.diogo.barbernet.api.domain.cabeleireiro;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosListagemCabeleireiro(Long id, String nome, String telefone, String email, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate admissao) {

    public DadosListagemCabeleireiro (Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome(), cabeleireiro.getTelefone(), cabeleireiro.getEmail(), cabeleireiro.getAdmissao());
    }
}
