package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.ponto.DadosDeEntrada;
import com.diogo.barbernet.api.domain.ponto.DadosDetalhamentoPontos;
import com.diogo.barbernet.api.services.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping("entrada")
    private ResponseEntity entrada (@RequestBody DadosDeEntrada dados) {
        this.pontoService.entrada(dados);
        return ResponseEntity.ok().build();
    }

    @PostMapping("saida")
    private ResponseEntity saida (@RequestBody DadosDeEntrada dados) {
        this.pontoService.saida(dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private ResponseEntity<List<DadosDetalhamentoPontos>> listarPontos() {
        var listar = pontoService.findAllpontos();
        return ResponseEntity.ok().body(listar);
    }

}
