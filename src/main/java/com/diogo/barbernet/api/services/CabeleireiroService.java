package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosAtulizacaoCabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosCadastroCabeleireiro;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabeleireiroService {

    @Autowired
    private CabeleireiroRepository repository;

    public Cabeleireiro cadastrarCabeleireiro(DadosCadastroCabeleireiro dados) {
        Cabeleireiro cabeleireiro = new Cabeleireiro(dados);
        return repository.save(cabeleireiro);
    }

    public List<Cabeleireiro> findAll() {
        return repository.findAll();
    }

    public Cabeleireiro atualizarCabeleireiro(Long id, DadosAtulizacaoCabeleireiro dados) {
        Cabeleireiro cabeleireiro = repository.getReferenceById(id);
        cabeleireiro.setNome(dados.nome());
        return cabeleireiro;
    }

    public void deletar(Long id) {
        Cabeleireiro cabeleireiro = repository.getReferenceById(id);
        repository.deleteById(id);
    }
}
