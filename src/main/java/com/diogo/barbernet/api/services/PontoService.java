package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.DadosDetalhamentoAgendamento;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.ponto.DadosDeEntrada;
import com.diogo.barbernet.api.domain.ponto.DadosDetalhamentoPontos;
import com.diogo.barbernet.api.domain.ponto.Ponto;
import com.diogo.barbernet.api.domain.ponto.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void saida(DadosDeEntrada dados) {
        Cabeleireiro cabeleireiro = cabeleireiroService.findByCpf(dados.cpf());
        Optional<Ponto> pontoOptional = pontoRepository.findByCabeleireiroCpf(cabeleireiro.getCpf());
        if (pontoOptional.isPresent()) {
            Ponto ponto = pontoOptional.get();
            ponto.setSaida(LocalDateTime.now());
            pontoRepository.save(ponto);
        } else {
            throw new ValidacaoException("Ponto não encontrado para o CPF: " + dados.cpf());
        }
    }


    public List<DadosDetalhamentoPontos> findAllpontos() {
        var lista = pontoRepository.findAll();
        return lista.stream()
                .map(DadosDetalhamentoPontos::new)
                .collect(Collectors.toList());
    }
}
