package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.cliente.DadosCadastroCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente cadastrarCliente(DadosCadastroCliente dados){
        Cliente newDados = new Cliente(dados);
        return repository.save(newDados);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }
}
