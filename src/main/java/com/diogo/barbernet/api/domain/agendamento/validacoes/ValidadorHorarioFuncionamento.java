package com.diogo.barbernet.api.domain.agendamento.validacoes;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeCorte{
    @Override
    public void validar(DadosAgendamentoCorte dados) {
        var dataCorte = dados.data();

        var antesDaAberturaDoCabeleireiro = dataCorte.getHour() < 8;
        var depoisDoEncerramentoDoCabeleireiro = dataCorte.getHour() > 20;
        if(antesDaAberturaDoCabeleireiro || depoisDoEncerramentoDoCabeleireiro){
            throw new ValidacaoException("Agendamento fora do horario de funcionamento!!");
        }
    }
}
