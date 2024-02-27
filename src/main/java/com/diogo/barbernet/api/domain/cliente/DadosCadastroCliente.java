package com.diogo.barbernet.api.domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(String nome, String telefone, String email, String cpf) {
}
