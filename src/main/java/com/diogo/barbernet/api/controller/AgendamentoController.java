package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.DadosAgendamentoCorte;
import com.diogo.barbernet.api.domain.agendamento.DadosDetalhamentoAgendamento;
import com.diogo.barbernet.api.domain.agendamento.DadosDetalhamentoAgendamentoId;
import com.diogo.barbernet.api.services.AgendamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agendamentos")
@SecurityRequirement(name = "bearer-key")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping(value = "{id}")
    public ResponseEntity<DadosDetalhamentoAgendamentoId> findById(@PathVariable Long id){
        Agendamento agendamento = agendamentoService.findById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoAgendamentoId(agendamento));
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendarCorte(@RequestBody @Valid DadosAgendamentoCorte dados){
        var agendar = agendamentoService.agendar(dados);
        return ResponseEntity.ok(agendar);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelarCorte(@PathVariable @Valid Long id){
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> listarConsulta(){
        List<Agendamento> listAgenda = agendamentoService.listarAgendamento();
        List<DadosDetalhamentoAgendamento> listar = listAgenda.stream()
                .map(DadosDetalhamentoAgendamento::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listar);
    }
}
