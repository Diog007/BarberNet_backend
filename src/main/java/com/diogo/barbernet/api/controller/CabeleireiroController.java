package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.cabeleireiro.*;
import com.diogo.barbernet.api.services.CabeleireiroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cabeleireiros")
@SecurityRequirement(name = "bearer-key")
public class CabeleireiroController {
    @Autowired
    private CabeleireiroService service;

    @PostMapping
    public ResponseEntity<DadosCadastroCabeleireiro> cadastrarCabeleireiro (@Valid @RequestBody DadosCadastroCabeleireiro dados){
        Cabeleireiro cabeleireiro = service.cadastrarCabeleireiro(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cabeleireiro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCabeleireiro>> listarCabeleireiro(){
        List<Cabeleireiro> list = service.findAll();
        List<DadosListagemCabeleireiro> listCabeleireiro = list.stream().map(DadosListagemCabeleireiro::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listCabeleireiro);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarCabeleireiro (@PathVariable Long id, @RequestBody @Valid DadosAtulizacaoCabeleireiro dados){
        this.service.atualizarCabeleireiro(id, dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCabeleireiro(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DadosDetalhamentoCabeleireiro> findById(@PathVariable Long id){
        var cabeleireiro = service.findById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoCabeleireiro(cabeleireiro));
    }

}
