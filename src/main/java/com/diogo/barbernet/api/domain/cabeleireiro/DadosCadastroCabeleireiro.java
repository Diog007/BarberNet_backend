package com.diogo.barbernet.api.domain.cabeleireiro;

import com.diogo.barbernet.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroCabeleireiro(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email(message = "Formato de email inálido!")
        String email,
        @CPF(message = "CPF inválido")
        @NotBlank
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
