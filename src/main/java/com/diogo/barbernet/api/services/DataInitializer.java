package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.agendamento.MetodoPagamento;
import com.diogo.barbernet.api.domain.agendamento.StatusAgendamento;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.usuario.Usuario;
import com.diogo.barbernet.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Override

    public void run(String... args) throws Exception {
        String login = "test@test.com";
        String senha = "123";
        String senhaCriptografada = passwordEncoder.encode(senha);
        Usuario usuario = new Usuario(null, login, senhaCriptografada);
        if (!usuarioRepository.existsByLogin(login)) {
            usuarioRepository.save(usuario);
        }

        Cliente cliente1 = new Cliente(1L, "Diogo", "11-95971-5957", "diogo@gmail.com", "1211515631");
        Cliente cliente2 = new Cliente(2L, "Malcon", "11-95843-9571", "malcon@gmail.com", "263515611521");
        Cliente cliente3 = new Cliente(3L, "Danilo", "11-95964-5157", "danilo@gmail.com", "15212611121");
        if (!clienteRepository.existsByCpf(cliente1.getCpf())) {
            clienteRepository.save(cliente1);
        }
        if (!clienteRepository.existsByCpf(cliente2.getCpf())) {
            clienteRepository.save(cliente2);
        }
        if (!clienteRepository.existsByCpf(cliente3.getCpf())) {
            clienteRepository.save(cliente3);
        }

        Cabeleireiro cabeleireiro1 = new Cabeleireiro(1L, "Robert", "11-92964-2157", "robert@gmail.com", "770.578.060-78");
        Cabeleireiro cabeleireiro2 = new Cabeleireiro(2L, "Renato", "11-91964-6157", "renato@gmail.com", "834.064.320-77");
        Cabeleireiro cabeleireiro3 = new Cabeleireiro(3L, "Omar", "11-95954-5857", "omar@gmail.com", "651.111.810-08");
        if (!cabeleireiroRepository.existsByCpf(cabeleireiro1.getCpf())) {
            cabeleireiroRepository.save(cabeleireiro1);
        }
        if (!cabeleireiroRepository.existsByCpf(cabeleireiro2.getCpf())) {
            cabeleireiroRepository.save(cabeleireiro2);
        }
        if (!cabeleireiroRepository.existsByCpf(cabeleireiro3.getCpf())) {
            cabeleireiroRepository.save(cabeleireiro3);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime dataHora1 = LocalDateTime.parse("2024-06-10T10:15:30");
        LocalDateTime dataHora2 = LocalDateTime.parse("2024-06-11T15:30:00", formatter);
        LocalDateTime dataHora3 = LocalDateTime.parse("2024-06-12T10:15:30", formatter);

        Agendamento agendamento1 = new Agendamento(null, cliente1, cabeleireiro1, LocalDate.now(), dataHora1, new BigDecimal("25.80"), StatusAgendamento.EM_ABERTO, MetodoPagamento.CARTAO_CREDITO, "sei la");
        Agendamento agendamento2 = new Agendamento(null, cliente2, cabeleireiro2, LocalDate.now(), dataHora1, new BigDecimal("50.20"), StatusAgendamento.CONCLUIDO, MetodoPagamento.CARTAO_DEBITO, "sei la");
        Agendamento agendamento3 = new Agendamento(null, cliente3, cabeleireiro3, LocalDate.now(), dataHora1, new BigDecimal("60.60"), StatusAgendamento.CANCELADO, MetodoPagamento.PIX,"sei la");
        agendamentoRepository.save(agendamento1);
        agendamentoRepository.save(agendamento2);
        agendamentoRepository.save(agendamento3);
    }
}

