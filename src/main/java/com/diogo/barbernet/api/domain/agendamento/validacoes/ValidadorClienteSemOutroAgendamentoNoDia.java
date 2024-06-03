package com.diogo.barbernet.api.domain.agendamento.validacoes;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteSemOutroAgendamentoNoDia implements ValidadorAgendamentoDeCorte{

    @Autowired
    private AgendamentoRepository repository;
    @Override
    public void validar(DadosAgendamentoCorte dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var clientePossuiOutroAgendamentoNoDia = repository.existsByClienteIdAndDataHoraBetween(dados.cliente(), primeiroHorario, ultimoHorario);
        if(clientePossuiOutroAgendamentoNoDia){
            throw new ValidacaoException("cliente ja possui agendamento marcado para este dia!");
        }
    }
}
