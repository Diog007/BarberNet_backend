package com.diogo.barbernet.api.domain.agendamento.validacoes;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import com.diogo.barbernet.api.domain.agendamento.StatusAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Component
public class ValidadorDeCabeleireiroComAgendamentoNoMesmoHorario implements ValidadorAgendamentoDeCorte {

    @Autowired
    private AgendamentoRepository repository;

    @Override
    public void validar(DadosAgendamentoCorte dados) {
        LocalDateTime dataHoraAgendamento = dados.data();
        LocalDateTime inicioIntervalo = dataHoraAgendamento.minus(40, ChronoUnit.MINUTES);
        LocalDateTime fimIntervalo = dataHoraAgendamento.plus(40, ChronoUnit.MINUTES);

        List<StatusAgendamento> statusExcluidos = Arrays.asList(StatusAgendamento.CONCLUIDO, StatusAgendamento.CANCELADO);

        boolean cabeleireiroPossuiOutraAgendamentoNoMesmoHorario = repository.existsByCabeleireiroIdAndDataHoraBetweenAndStatusNotIn(
                dados.cabeleireiro(), inicioIntervalo, fimIntervalo, statusExcluidos
        );

        if (cabeleireiroPossuiOutraAgendamentoNoMesmoHorario) {
            throw new ValidacaoException("Cabeleireiro já possui um agendamento para este horário ou dentro de um intervalo de 40 minutos!");
        }
    }
}
