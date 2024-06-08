package com.diogo.barbernet.api.domain.agendamento.validacoes;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeCorte{
    @Override
    public void validar(DadosAgendamentoCorte dados) {
        var dataCorte = dados.data();
        var agora = LocalDateTime.now();
        var diferancaEmMinutos = Duration.between(agora, dataCorte).toMinutes();

        if(diferancaEmMinutos < 30) {
            throw  new ValidacaoException("Agendamento deve ser com 30 minutos de antecedência!");
        }
    }
}
