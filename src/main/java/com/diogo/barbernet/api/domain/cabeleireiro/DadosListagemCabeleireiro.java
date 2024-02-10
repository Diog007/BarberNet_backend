package com.diogo.barbernet.api.domain.cabeleireiro;

public record DadosListagemCabeleireiro(Long id, String nome) {

    public DadosListagemCabeleireiro (Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome());
    }
}
