package com.diogo.barbernet.api.domain.agendamento;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoCorte {
    @Autowired
    private CabeleireiroRepository cabeleireiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;


    public DadosDetalhamentoAgendamento agendar(DadosAgendamentoCorte dados) {
        Optional<Cliente> cliente = clienteRepository.findById(dados.idCliente());
        Optional<Cabeleireiro> cabeleireiro = cabeleireiroRepository.findById(dados.idCabeleireiro());
        if (cliente.isPresent() && cabeleireiro.isPresent()) {
            Cliente clientes = cliente.get();
            Cabeleireiro cabeleireiros = cabeleireiro.get();

            Agendamento agendamento = new Agendamento(null, clientes, cabeleireiros, dados.data());
            agendamentoRepository.save(agendamento);
            return new DadosDetalhamentoAgendamento(agendamento);
        }
        return null;
    }


    public void cancelarAgendamento (Long id) {
        Agendamento agenda = agendamentoRepository.findById(id).orElse(null);
        agendamentoRepository.deleteById(agenda.getId());
    }
}
