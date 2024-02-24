package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;


    public List<Agendamento> listarAgendamento() {
        return repository.findAll();
    }

}
