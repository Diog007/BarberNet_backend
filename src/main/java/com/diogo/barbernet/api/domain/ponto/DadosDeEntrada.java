package com.diogo.barbernet.api.domain.ponto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record DadosDeEntrada(
        @NotBlank
        @CPF
        String cpf
) {
}
