package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosAtulizacaoCabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosCadastroCabeleireiro;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabeleireiroService {

    @Autowired
    private CabeleireiroRepository repository;

    public Cabeleireiro cadastrarCabeleireiro(DadosCadastroCabeleireiro dados) {
        validaPorCpfEEmail(dados);
        Cabeleireiro cabeleireiro = new Cabeleireiro(dados);
        return repository.save(cabeleireiro);
    }

    public List<Cabeleireiro> findAll() {
        return repository.findAll();
    }

    public Cabeleireiro atualizarCabeleireiro(Long id, DadosAtulizacaoCabeleireiro dados) {
        Cabeleireiro cabeleireiro = findById(id);
        cabeleireiro.setNome(dados.nome());
        cabeleireiro.setTelefone(dados.telefone());
        cabeleireiro.setEmail(dados.email());
        cabeleireiro.setCpf(dados.cpf());
        return repository.save(cabeleireiro);
    }

    public void deletar(Long id) {
        Cabeleireiro cabeleireiro = repository.getReferenceById(id);
        if(cabeleireiro.getAgendamentos().size() > 0) {
            throw new ValidacaoException("cabeleireiros possui agendamentos");
        }
        repository.deleteById(id);
    }

    public Cabeleireiro findById(Long id) {
        var cabeleireiro = repository.findById(id);
        return cabeleireiro.orElseThrow(() -> new ValidacaoException("Cabeleireiro não encontrado"));
    }

    private void validaPorCpfEEmail(DadosCadastroCabeleireiro cabeleireiro) {
        Optional<Cabeleireiro> obj = repository.findByCpf(cabeleireiro.cpf());
        if(obj.isPresent()){
            throw new ValidacaoException("CPF já cadastrado no sistema!");
        }
        obj = repository.findByEmail(cabeleireiro.email());
        if(obj.isPresent()) {
            throw new ValidacaoException("E-mail já cadastrado no sistema!");
        }
    }

    public Cabeleireiro findByCpf(String cpf) {
        var cabeleireiro = repository.findByCpf(cpf);
        return cabeleireiro.orElse(null);
    }
}
