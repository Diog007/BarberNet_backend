package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.*;
import com.diogo.barbernet.api.domain.agendamento.validacoes.ValidadorAgendamentoDeCorte;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CabeleireiroRepository cabeleireiroRepository;

    @Autowired
    private List<ValidadorAgendamentoDeCorte> validadores;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private  CabeleireiroService cabeleireiroService;

    public List<Agendamento> listarAgendamento() {
        return repository.findAll();
    }

    public DadosDetalhamentoAgendamento agendar(DadosAgendamentoCorte dados) {
        if (!clienteRepository.existsById(Long.valueOf(dados.cliente()))) {
            throw new ValidacaoException("Id do cliente informado nao existe!");
        }
        if (dados.cabeleireiro() != null && !cabeleireiroRepository.existsById(Long.valueOf(dados.cabeleireiro()))) {
            throw new ValidacaoException("Id do cabeleireiro informado nao existe!");
        }
        validadores.forEach(v -> v.validar(dados));

        var cliente = clienteRepository.getReferenceById(dados.cliente());
        var cabeleireiro = cabeleireiroRepository.getReferenceById(dados.cabeleireiro());

        var agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setCabeleireiro(cabeleireiro);
        agendamento.setDataHora(dados.data());
        agendamento.setPrecoEstimado(dados.precoEstimado());
        agendamento.setStatus(dados.statusAgendamento());
        agendamento.setMetodoPagamento(dados.metodoPagamento());
        agendamento.setObservacao(dados.observacao());
        agendamentoRepository.save(agendamento);

        return new DadosDetalhamentoAgendamento(agendamento);
    }

    public void cancelarAgendamento (Long id) {
        Agendamento agenda = this.findById(id);
        agendamentoRepository.deleteById(agenda.getId());
    }

    public Agendamento findById(Long id) {
        Optional<Agendamento> agendamento = repository.findById(id);
        return agendamento.orElseThrow(() -> new ValidacaoException("Objeto não encontrado! ID:" + id));
    }

    public void update(Long id, DadosAtualizarCorte dados) {
        Agendamento agendamentoUpdate = findById(id);
        var cabeleireiro = cabeleireiroService.findById(Long.valueOf(dados.cabeleireiro()));
        var cliente = clienteService.findById(Long.valueOf(dados.cliente()));

        agendamentoUpdate.setCabeleireiro(cabeleireiro);
        agendamentoUpdate.setCliente(cliente);
        agendamentoUpdate.setStatus(dados.statusAgendamento());
        agendamentoUpdate.setDataHora(dados.data());
        agendamentoUpdate.setDataCriacao(LocalDate.now());
        agendamentoUpdate.setPrecoEstimado(dados.precoEstimado());
        agendamentoUpdate.setMetodoPagamento(dados.metodoPagamento());
        agendamentoUpdate.setObservacao(dados.observacao());

        this.repository.save(agendamentoUpdate);
    }

    public void atualizarStatus(Long id, DadoAtualizarStatus atualizarStatus) {
        Agendamento agend = findById(id);
        agend.setStatus(atualizarStatus.status());
        repository.save(agend);
    }
}
