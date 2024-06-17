package com.diogo.barbernet.api.domain.cabeleireiro;

import com.diogo.barbernet.api.domain.endereco.Endereco;

public record DadosDetalhamentoCabeleireiro(Long id, String nome, String telefone, String email, String cpf, Endereco endereco) {

    public DadosDetalhamentoCabeleireiro(Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome(), cabeleireiro.getTelefone(), cabeleireiro.getEmail(), cabeleireiro.getCpf(), cabeleireiro.getEndereco());
    }
}
