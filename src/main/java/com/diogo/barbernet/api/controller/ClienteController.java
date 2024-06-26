package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.cliente.*;
import com.diogo.barbernet.api.services.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<DadosCadastroCliente> cadastrarCliente (@Valid @RequestBody DadosCadastroCliente dados){
        Cliente cliente = service.cadastrarCliente(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listarCliente(){
        List<Cliente> list = service.findAll();
        List<DadosListagemCliente> listCliente = list.stream().map(DadosListagemCliente::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosAtulizacaoCliente> atualizarCliente (@PathVariable Long id, @RequestBody @Valid DadosAtulizacaoCliente dados){
        Cliente cliente = service.atualizarCliente(id, dados);
        return ResponseEntity.ok().body(new DadosAtulizacaoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCliente(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DadosDetalhamentoCliente> findById(@PathVariable Long id){
        var cliente = service.findById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoCliente(cliente));
    }

}
