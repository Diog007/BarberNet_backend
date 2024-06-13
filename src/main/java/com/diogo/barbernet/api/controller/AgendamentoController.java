package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.agendamento.*;
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

    @PutMapping("/status/{id}")
    public ResponseEntity atualizarStqatus(@PathVariable @Valid Long id, @RequestBody DadoAtualizarStatus atualizarStatus) {
        agendamentoService.atualizarStatus(id, atualizarStatus);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> listarAgendamento(){
        List<Agendamento> listAgenda = agendamentoService.listarAgendamento();
        List<DadosDetalhamentoAgendamento> listar = listAgenda.stream()
                .map(DadosDetalhamentoAgendamento::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listar);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAgendamento(@PathVariable Long id, @RequestBody DadosAtualizarCorte dados) {
        this.agendamentoService.update(id, dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cabeleireiro/{id}")
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> getAgendamentosByCabeleireiroId(@PathVariable Long id) {
        List<DadosDetalhamentoAgendamento> agendamentos = agendamentoService.findAllByAgendamentosPorCabeId(id);
        return ResponseEntity.ok().body(agendamentos);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> getAgendamentosByClienteId(@PathVariable Long id) {
        List<DadosDetalhamentoAgendamento> agendamentos = agendamentoService.findAllByAgendamentosPorCLiId(id);
        return ResponseEntity.ok().body(agendamentos);
    }
}
