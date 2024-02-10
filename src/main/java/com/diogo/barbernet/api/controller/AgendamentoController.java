package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.agendamento.AgendamentoCorte;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import com.diogo.barbernet.api.domain.agendamento.DadosCancelamentoCorte;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarCorte(@RequestBody @Valid DadosCancelamentoCorte dados){
        agendamentoCorte.cancelarAgendamento(dados);
        return ResponseEntity.noContent().build();
    }

}
