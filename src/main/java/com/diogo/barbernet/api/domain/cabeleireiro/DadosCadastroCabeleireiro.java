package com.diogo.barbernet.api.domain.cabeleireiro;

import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroCabeleireiro(String nome, String telefone, String email, @CPF String cpf) {
}
