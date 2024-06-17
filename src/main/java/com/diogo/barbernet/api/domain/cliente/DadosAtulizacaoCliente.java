package com.diogo.barbernet.api.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record DadosAtulizacaoCliente(Long id,
                                     @NotBlank(message = "O nome não pode estar em branco")
                                     String nome,
                                     @NotBlank
                                     String telefone,
                                     @NotBlank
                                     @Email(message = "Formato de email inálido!")
                                     String email,
                                     @NotBlank
                                     @CPF(message = "CPF inválido")
                                     String cpf
) {


    public DadosAtulizacaoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf());
    }
}
