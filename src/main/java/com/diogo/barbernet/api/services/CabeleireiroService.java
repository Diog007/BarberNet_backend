package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.controller.EmailController;
import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.DadosDetalhamentoAgendamento;
import com.diogo.barbernet.api.domain.cabeleireiro.*;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.email.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CabeleireiroService {

    @Autowired
    private CabeleireiroRepository repository;

    @Autowired
    private EmailController emailController;

    public Cabeleireiro cadastrarCabeleireiro(DadosCadastroCabeleireiro dados) {
        validaPorCpfEEmail(dados);
        Cabeleireiro cabeleireiro = new Cabeleireiro(dados);
        sendEmailCliente(cabeleireiro);
        return repository.save(cabeleireiro);
    }

    public List<Cabeleireiro> findAll() {
        return repository.findAll();
    }

    public void atualizarCabeleireiro(Long id, DadosAtulizacaoCabeleireiro dados) {
        Cabeleireiro cabeleireiro = findById(id);
        cabeleireiro.atualizar(dados);
        repository.save(cabeleireiro);
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
        return cabeleireiro.orElseThrow(() -> new ValidacaoException("CPF não encontrado!"));
    }

    private void sendEmailCliente(Cabeleireiro cabeleireiro) {
        String assunto = "Bem-vindo(a) à Barbernet!";
        String mensagem = String.format(
                "Olá, %s,\n\n" +
                        "Seja bem-vindo(a) à nossa equipe de colaboradores da barbearia! Estamos muito contentes em tê-lo(a) conosco.\n" +
                        "Aqui estão alguns detalhes do seu cadastro:\n\n" +
                        "Nome: %s\n" +
                        "E-mail: %s\n" +
                        "Telefone: %s\n" +
                        "CPF: %s\n" +
                        "Endereço:\n" +
                        "Logradouro: %s\n" +
                        "Bairro: %s\n" +
                        "CEP: %s\n" +
                        "Cidade: %s\n" +
                        "UF: %s\n" +
                        "Número: %s\n\n" +
                        "Se precisar de qualquer coisa, não hesite em nos contatar.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe Barbernet",
                cabeleireiro.getNome(), cabeleireiro.getNome(), cabeleireiro.getEmail(), cabeleireiro.getTelefone(), cabeleireiro.getCpf(),
                cabeleireiro.getEndereco().getLogradouro(), cabeleireiro.getEndereco().getBairro(), cabeleireiro.getEndereco().getCep(),
                cabeleireiro.getEndereco().getCidade(), cabeleireiro.getEndereco().getUf(),
                cabeleireiro.getEndereco().getNumero()
        );

        EmailDTO email = new EmailDTO("barbernet.api@gmail.com", cabeleireiro.getEmail(), assunto, mensagem);
        this.emailController.sendingEmail(email);
    }

}
