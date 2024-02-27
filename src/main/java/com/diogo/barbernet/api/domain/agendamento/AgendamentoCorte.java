package com.diogo.barbernet.api.domain.agendamento;

import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.validacoes.ValidadorAgendamentoDeCorte;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoCorte {
    @Autowired
    private CabeleireiroRepository cabeleireiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private List<ValidadorAgendamentoDeCorte> validadores;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamentoCorte dados) {
        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Id do paciente informado nao existe!");
        }
        if (dados.idCabeleireiro() != null && !cabeleireiroRepository.existsById(dados.idCabeleireiro())) {
            throw new ValidacaoException("Id do medico informado nao existe!");
        }
        validadores.forEach(v -> v.validar(dados));

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var cabeleireiros = cabeleireiroRepository.getReferenceById(dados.idCabeleireiro());

        var agendamento = new Agendamento(null, cliente, cabeleireiros, dados.data());
        agendamentoRepository.save(agendamento);

        return new DadosDetalhamentoAgendamento(agendamento);
    }


    public void cancelarAgendamento (Long id) {
        Agendamento agenda = agendamentoRepository.findById(id).orElse(null);
        agendamentoRepository.deleteById(agenda.getId());
    }
}
