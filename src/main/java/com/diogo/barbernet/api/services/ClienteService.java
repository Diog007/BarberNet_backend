package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.cliente.DadosAtulizacaoCliente;
import com.diogo.barbernet.api.domain.cliente.DadosCadastroCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Long id) {
        Optional<Cliente> optionalCliente = repository.findById(id);
        return optionalCliente.orElse(null);
    }
    public Cliente cadastrarCliente(DadosCadastroCliente dados){
        Cliente newDados = new Cliente(dados);
        return repository.save(newDados);
    }
    public List<Cliente> findAll() {
        return repository.findAll();
    }
    public Cliente atualizarCliente(DadosAtulizacaoCliente dados) {
        Cliente cliente = findById(dados.id());
        cliente.setNome(dados.nome());
        cliente.setTelefone(dados.telefone());
        cliente.setEmail(dados.email());
        cliente.setCpf(dados.cpf());

        return repository.save(cliente);
    }
    public void deletar(Long id) {
        Cliente cliente = findById(id);
        repository.deleteById(id);
    }
}
