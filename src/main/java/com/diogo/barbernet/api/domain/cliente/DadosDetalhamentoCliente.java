package com.diogo.barbernet.api.domain.cliente;

public record DadosDetalhamentoCliente(Long id, String nome, String telefone, String email, String cpf) {

    public DadosDetalhamentoCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf());
    }
}
