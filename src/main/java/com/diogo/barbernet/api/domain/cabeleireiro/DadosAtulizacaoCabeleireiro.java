package com.diogo.barbernet.api.domain.cabeleireiro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record DadosAtulizacaoCabeleireiro(
        Long id,
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email(message = "Formato de email inálido!")
        String email,
        @CPF(message = "CPF inválido")
        @NotBlank
        String cpf) {

    public DadosAtulizacaoCabeleireiro(Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome(), cabeleireiro.getTelefone(), cabeleireiro.getEmail(), cabeleireiro.getCpf());
    }
}
