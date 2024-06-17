package com.diogo.barbernet.api.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email(message = "Formato de email inálido!")
        String email,
        String cpf) {
}
