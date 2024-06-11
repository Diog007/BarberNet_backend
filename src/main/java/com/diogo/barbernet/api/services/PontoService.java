package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.ponto.DadosDeEntrada;
import com.diogo.barbernet.api.domain.ponto.Ponto;
import com.diogo.barbernet.api.domain.ponto.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private CabeleireiroService cabeleireiroService;

    public void entrada(DadosDeEntrada dados) {
        Cabeleireiro cabeleireiro = cabeleireiroService.findByCpf(dados.cpf());
        Ponto ponto = new Ponto();
        ponto.setCabeleireiro(cabeleireiro);
        ponto.setEntrada(LocalDateTime.now());
        pontoRepository.save(ponto);
    }

}
