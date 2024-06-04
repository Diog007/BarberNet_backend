package com.diogo.barbernet.api.domain.agendamento.validacoes;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeCabeleireiroComAgendamentoNoMesmoHorario implements ValidadorAgendamentoDeCorte{

    @Autowired
    private AgendamentoRepository repository;

    @Override
    public void validar(DadosAgendamentoCorte dados) {
        var cabeleireiroPossuiOutraAgendamentoNoMesmoHorario = repository.existsByCabeleireiroIdAndDataHora(dados.cabeleireiro(), dados.data());
        if(cabeleireiroPossuiOutraAgendamentoNoMesmoHorario){
            throw  new ValidacaoException("Cabeleireiro já possui um agendamento para este horario!!");
        }
    }
}
