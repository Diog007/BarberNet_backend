package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.agendamento.*;
import com.diogo.barbernet.api.services.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoCorte agendamentoCorte;
    @Autowired
    private AgendamentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosAgendamentoCorte> agendarCorte(@RequestBody @Valid DadosAgendamentoCorte dados){
        var agendar = agendamentoCorte.agendar(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agendar.id()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarCorte(@RequestBody @Valid DadosCancelamentoCorte dados){
        agendamentoCorte.cancelarAgendamento(dados);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> listarConsulta(){
        List<Agendamento> listAgenda = service.listarAgendamento();
        List<DadosDetalhamentoAgendamento> listar = listAgenda.stream()
                .map(DadosDetalhamentoAgendamento::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listar);
    }
}
