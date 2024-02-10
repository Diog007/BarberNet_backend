package com.diogo.barbernet.api.domain.cabeleireiro;

public record DadosAtulizacaoCabeleireiro(Long id, String nome) {

    public DadosAtulizacaoCabeleireiro(Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome());
    }
}
