package com.diogo.barbernet.api.domain.cabeleireiro;

public record DadosListagemCabeleireiro(Long id, String nome, String telefone, String email) {

    public DadosListagemCabeleireiro (Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome(), cabeleireiro.getTelefone(), cabeleireiro.getEmail());
    }
}
