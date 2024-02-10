package com.diogo.barbernet.api.domain.cabeleireiro;

public record DadosListagemCabeleireiro(String nome) {

    public DadosListagemCabeleireiro (Cabeleireiro cabeleireiro){
        this(cabeleireiro.getNome());
    }
}
