package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.agendamento.AgendamentoCorte;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoCorte agendamentoCorte;

    @PostMapping
    @Transactional
    public ResponseEntity agendarCorte(@RequestBody @Valid DadosAgendamentoCorte dados){
        var agendar = agendamentoCorte.agendar(dados);
        return ResponseEntity.ok(agendar);
    }

}
