package com.diogo.barbernet.api.domain.agendamento;

import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoCorte {
    @Autowired
    private CabeleireiroRepository cabeleireiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;


    public Object agendar(DadosAgendamentoCorte dados) {
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var cabeleireiro = cabeleireiroRepository.getReferenceById(dados.idCabeleireiro());

        var agendamento = new Agendamento(null, cliente, cabeleireiro, dados.data());
        agendamentoRepository.save(agendamento);
        return new DadosDetalhamentoAgendamento(agendamento);
    }
}
