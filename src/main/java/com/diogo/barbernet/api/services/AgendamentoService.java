package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.controller.EmailController;
import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.agendamento.*;
import com.diogo.barbernet.api.domain.agendamento.validacoes.ValidadorAgendamentoDeCorte;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.email.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private EmailController emailController;

    public List<Agendamento> listarAgendamento() {
        return repository.findAll();
    }

    public DadosDetalhamentoAgendamento agendar(DadosAgendamentoCorte dados) {
        validadores.forEach(v -> v.validar(dados));
        Cliente cliente = clienteRepository.getReferenceById(dados.cliente());
        Cabeleireiro cabeleireiro = cabeleireiroRepository.getReferenceById(dados.cabeleireiro());

        Agendamento agendamento = new Agendamento(dados, cliente, cabeleireiro);

        agendamentoRepository.save(agendamento);

        sendEmailAgendamento(cliente, agendamento);

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

    public List<DadosDetalhamentoAgendamento> findAllByAgendamentosPorCabeId(Long id) {
        var agendamentos = repository.findAllByCabeleireiroId(id);
        return agendamentos.stream()
                .map(DadosDetalhamentoAgendamento::new)
                .collect(Collectors.toList());
    }

    public List<DadosDetalhamentoAgendamento> findAllByAgendamentosPorCLiId(Long id) {
        var agendamentos = repository.findAllByClienteId(id);
        return agendamentos.stream()
                .map(DadosDetalhamentoAgendamento::new)
                .collect(Collectors.toList());
    }

    private void sendEmailAgendamento(Cliente cliente, Agendamento agendamento) {
        String assunto = "Confirmação de Agendamento";
        String mensagem = String.format(
                "Olá, %s,\n\n" +
                        "Seu agendamento foi confirmado com sucesso! Aqui estão os detalhes:\n\n" +
                        "Nome do Cliente: %s\n" +
                        "Nome do Cabeleireiro: %s\n" +
                        "Data e Hora: %s\n" +
                        "Preço Estimado: R$ %.2f\n" +
                        "Método de Pagamento: %s\n" +
                        "Observação: %s\n\n" +
                        "Se precisar de qualquer coisa, não hesite em nos contatar.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe Barbernet",
                cliente.getNome(),
                cliente.getNome(),
                agendamento.getCabeleireiro().getNome(),
                agendamento.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                agendamento.getPrecoEstimado(),
                agendamento.getMetodoPagamento().toString(),
                agendamento.getObservacao()
        );

        EmailDTO email = new EmailDTO("barbernet.api@gmail.com", cliente.getEmail(), assunto, mensagem);
        emailController.sendingEmail(email);
    }
}
