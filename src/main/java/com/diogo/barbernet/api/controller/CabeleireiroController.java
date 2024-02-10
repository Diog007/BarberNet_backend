package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosCadastroCabeleireiro;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.DadosCadastroCliente;
import com.diogo.barbernet.api.services.CabeleireiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class CabeleireiroController {
    @Autowired
    private CabeleireiroService service;

    @PostMapping
    public ResponseEntity<DadosCadastroCabeleireiro> cadastrarCabeleireiro (@Valid @RequestBody DadosCadastroCabeleireiro dados){
        Cabeleireiro cabeleireiro = service.cadastrarCabeleireiro(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cabeleireiro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
