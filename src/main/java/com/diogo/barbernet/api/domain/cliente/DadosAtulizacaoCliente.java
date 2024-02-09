package com.diogo.barbernet.api.domain.cliente;

public record DadosAtulizacaoCliente(Long id, String nome, String telefone) {


    public DadosAtulizacaoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }
}
