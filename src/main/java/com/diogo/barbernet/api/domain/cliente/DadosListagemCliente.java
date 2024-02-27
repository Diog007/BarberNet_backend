package com.diogo.barbernet.api.domain.cliente;

public record DadosListagemCliente(Long id, String nome, String telefone, String email) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail());
    }
}
