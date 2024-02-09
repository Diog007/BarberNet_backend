package com.diogo.barbernet.api.domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
       @NotBlank
        String nome,
       @NotBlank
       String telefone) {
}
