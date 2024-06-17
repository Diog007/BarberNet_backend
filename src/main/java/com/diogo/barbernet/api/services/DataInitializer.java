package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.agendamento.MetodoPagamento;
import com.diogo.barbernet.api.domain.agendamento.StatusAgendamento;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.endereco.DadosEndereco;
import com.diogo.barbernet.api.domain.endereco.Endereco;
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
                new Cliente(1L, "Diogo do Nascimento", "11-95971-5962", "diogo@gmail.com", "74931640028"),
                new Cliente(2L, "Malcon Souza", "11-95843-9571", "malcon@gmail.com", "80305192060"),
                new Cliente(3L, "Danilo Santos", "11-95964-5112", "danilo@gmail.com", "15394951080"),
                new Cliente(4L, "Lucas da Silva", "11-5161-0879", "lucas@gmail.com", "88367550030")
       );
        for (Cliente cliente : clientes) {
            if (!clienteRepository.existsByCpf(cliente.getCpf())) {
                clienteRepository.save(cliente);
            }
        }

        DadosEndereco endMed1 = new DadosEndereco("Avenida Paulista", "Bela Vista", "01311300", "São Paulo", "SP",  "15");
        Endereco enderecoMed1 = new Endereco(endMed1);
        DadosEndereco endMed2 = new DadosEndereco("Rua Augusta", "Consolação", "01304900", "São Paulo", "SP",  "545");
        Endereco enderecoMed2 = new Endereco(endMed2);
        DadosEndereco endMed3 = new DadosEndereco("Rua Oscar Freire", "Jardins", "01426000", "São Paulo", "SP",  "85");
        Endereco enderecoMed3 = new Endereco(endMed3);
        DadosEndereco endMed4 = new DadosEndereco("Avenida Brigadeiro Faria Lima", "Itaim Bibi", "01452000", "São Paulo", "SP",  "17");
        Endereco enderecoMed4 = new Endereco(endMed4);

        List<Cabeleireiro> cabeleireiros = List.of(
                new Cabeleireiro(1L, "Robert do Santos", "11-92964-2158", "robert@gmail.com", "74984302070", enderecoMed1),
                new Cabeleireiro(2L, "Renato da Silva", "11-91964-6152", "renato@gmail.com", "12041245077", enderecoMed2),
                new Cabeleireiro(3L, "Omar Rodrigues", "11-95954-5896", "omar@gmail.com", "40913283002", enderecoMed3),
                new Cabeleireiro(4L, "Fabiano Nascimento", "11-91514-2541", "fabiano@gmail.com", "00814078079", enderecoMed4)

        );
        for (Cabeleireiro cabeleireiro : cabeleireiros) {
            if (!cabeleireiroRepository.existsByCpf(cabeleireiro.getCpf())) {
                cabeleireiroRepository.save(cabeleireiro);
            }
        }

        List<Agendamento> agendamentos = List.of(
                new Agendamento(null, clientes.get(0), cabeleireiros.get(0), LocalDate.now(), LocalDateTime.parse("2024-06-24T10:15:30"), new BigDecimal("25.80"), StatusAgendamento.EM_ABERTO, MetodoPagamento.CARTAO_CREDITO, "Corte Social"),
                new Agendamento(null, clientes.get(1), cabeleireiros.get(1), LocalDate.now(), LocalDateTime.parse("2024-06-24T12:30:00"), new BigDecimal("50.20"), StatusAgendamento.CONCLUIDO, MetodoPagamento.CARTAO_DEBITO, "Corte moicano"),
                new Agendamento(null, clientes.get(2), cabeleireiros.get(2), LocalDate.now(), LocalDateTime.parse("2024-06-24T14:15:30"), new BigDecimal("60.60"), StatusAgendamento.CANCELADO, MetodoPagamento.PIX, "corte Degradê "),
                new Agendamento(null, clientes.get(3), cabeleireiros.get(3), LocalDate.now(), LocalDateTime.parse("2024-06-24T16:00:00"), new BigDecimal("70.70"), StatusAgendamento.EM_ABERTO, MetodoPagamento.DINHEIRO, "Corte repicado")

        );
        for (Agendamento agendamento : agendamentos) {
            agendamentoRepository.save(agendamento);
        }

    }

    }

