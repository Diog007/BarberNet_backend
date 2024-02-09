package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.DadosCadastroCliente;
import com.diogo.barbernet.api.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<DadosCadastroCliente> cadastrarCliente (@Valid @RequestBody DadosCadastroCliente dados){
        Cliente cliente = service.cadastrarCliente(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

}
