package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.agendamento.MetodoPagamento;
import com.diogo.barbernet.api.domain.agendamento.StatusAgendamento;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.ponto.Ponto;
import com.diogo.barbernet.api.domain.ponto.PontoRepository;
import com.diogo.barbernet.api.domain.usuario.Usuario;
import com.diogo.barbernet.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CabeleireiroRepository cabeleireiroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public void run(String... args) throws Exception {
        String login = "test@test.com";
        String senha = "123";
        String senhaCriptografada = passwordEncoder.encode(senha);
        Usuario usuario = new Usuario(null, login, senhaCriptografada);
        if (!usuarioRepository.existsByLogin(login)) {
            usuarioRepository.save(usuario);
        }

        List<Cliente> clientes = List.of(
                new Cliente(1L, "Diogo", "11-95971-5957", "diogo@gmail.com", "1211515631"),
                new Cliente(2L, "Malcon", "11-95843-9571", "malcon@gmail.com", "263515611521"),
                new Cliente(3L, "Danilo", "11-95964-5157", "danilo@gmail.com", "15212611121"),
                new Cliente(4L, "Lucas", "11-99999-9999", "lucas@gmail.com", "12345678901"),
                new Cliente(5L, "Pedro", "11-88888-8888", "pedro@gmail.com", "10987654321"),
                new Cliente(6L, "Carlos", "11-77777-7777", "carlos@gmail.com", "21345678901"),
                new Cliente(7L, "Julia", "11-66666-6666", "julia@gmail.com", "32345678901"));
        for (Cliente cliente : clientes) {
            if (!clienteRepository.existsByCpf(cliente.getCpf())) {
                clienteRepository.save(cliente);
            }
        }

        List<Cabeleireiro> cabeleireiros = List.of(
                new Cabeleireiro(1L, "Robert do Santos", "11-92964-2157", "robert@gmail.com", "77057806078"),
                new Cabeleireiro(2L, "Renato", "11-91964-6157", "renato@gmail.com", "83406432077"),
                new Cabeleireiro(3L, "Omar", "11-95954-5857", "omar@gmail.com", "65111181008"),
                new Cabeleireiro(4L, "Ana", "11-77777-7777", "ana@gmail.com", "12345678901"),
                new Cabeleireiro(5L, "Maria", "11-66666-6666", "maria@gmail.com", "10987654321"),
                new Cabeleireiro(6L, "João", "11-55555-5555", "joao@gmail.com", "20987654321"),
                new Cabeleireiro(7L, "Clara", "11-44444-4444", "clara@gmail.com", "30987654321")

        );
        for (Cabeleireiro cabeleireiro : cabeleireiros) {
            if (!cabeleireiroRepository.existsByCpf(cabeleireiro.getCpf())) {
                cabeleireiroRepository.save(cabeleireiro);
            }
        }

        List<Agendamento> agendamentos = List.of(
                new Agendamento(null, clientes.get(0), cabeleireiros.get(0), LocalDate.now(), LocalDateTime.parse("2024-06-10T14:15:30"), new BigDecimal("25.80"), StatusAgendamento.EM_ABERTO, MetodoPagamento.CARTAO_CREDITO, "sei la"),
                new Agendamento(null, clientes.get(1), cabeleireiros.get(1), LocalDate.now(), LocalDateTime.parse("2024-06-11T14:30:00"), new BigDecimal("50.20"), StatusAgendamento.CONCLUIDO, MetodoPagamento.CARTAO_DEBITO, "sei la"),
                new Agendamento(null, clientes.get(2), cabeleireiros.get(2), LocalDate.now(), LocalDateTime.parse("2024-06-12T14:15:30"), new BigDecimal("60.60"), StatusAgendamento.CANCELADO, MetodoPagamento.PIX, "sei la"),
                new Agendamento(null, clientes.get(3), cabeleireiros.get(3), LocalDate.now(), LocalDateTime.parse("2024-06-13T15:00:00"), new BigDecimal("70.70"), StatusAgendamento.EM_ABERTO, MetodoPagamento.DINHEIRO, "sei la"),
                new Agendamento(null, clientes.get(4), cabeleireiros.get(4), LocalDate.now(), LocalDateTime.parse("2024-06-14T16:30:00"), new BigDecimal("80.80"), StatusAgendamento.CONCLUIDO, MetodoPagamento.CARTAO_DEBITO, "sei la"),
                new Agendamento(null, clientes.get(5), cabeleireiros.get(5), LocalDate.now(), LocalDateTime.parse("2024-06-15T17:00:00"), new BigDecimal("90.90"), StatusAgendamento.CANCELADO, MetodoPagamento.CARTAO_CREDITO, "sei la"),
                new Agendamento(null, clientes.get(6), cabeleireiros.get(6), LocalDate.now(), LocalDateTime.parse("2024-06-16T10:00:00"), new BigDecimal("100.00"), StatusAgendamento.EM_ABERTO, MetodoPagamento.DINHEIRO, "sei la")

        );
        for (Agendamento agendamento : agendamentos) {
            agendamentoRepository.save(agendamento);
        }

        var cab = new Cabeleireiro(1L, "Robert", "11-92964-2157", "robert@gmail.com", "770.578.060-78");

        Ponto ponto1 = new Ponto(null, cab, LocalDateTime.now().minusHours(8), LocalDateTime.now().minusHours(4));


        pontoRepository.save(ponto1);

    }

    }

