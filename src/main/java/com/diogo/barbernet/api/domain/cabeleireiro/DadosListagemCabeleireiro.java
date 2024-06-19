package com.diogo.barbernet.api.domain.cabeleireiro;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosListagemCabeleireiro(Long id, String nome, String cpf, String telefone, String email, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate admissao) {

    public DadosListagemCabeleireiro(Cabeleireiro cabeleireiro) {
        this(
                cabeleireiro.getId(),
                cabeleireiro.getNome(),
                cabeleireiro.getCpf() != null ? cabeleireiro.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4") : null,
                cabeleireiro.getTelefone(),
                cabeleireiro.getEmail(),
                cabeleireiro.getAdmissao()
        );
    }
}
