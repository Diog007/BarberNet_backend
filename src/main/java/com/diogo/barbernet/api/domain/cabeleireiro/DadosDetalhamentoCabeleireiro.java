package com.diogo.barbernet.api.domain.cabeleireiro;

public record DadosDetalhamentoCabeleireiro(Long id, String nome, String telefone, String email, String cpf) {

    public DadosDetalhamentoCabeleireiro(Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome(), cabeleireiro.getTelefone(), cabeleireiro.getEmail(), cabeleireiro.getCpf());
    }
}
