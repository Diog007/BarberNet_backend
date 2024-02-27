package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
            String login = "test@test.com";
            String senha = "123";
            String senhaCriptografada = passwordEncoder.encode(senha);
            Usuario usuario = new Usuario(null, login, senhaCriptografada);
            if(!usuarioRepository.existsByLogin(login)) {
                usuarioRepository.save(usuario);
            }

        Cliente cliente1 = new Cliente(11L, "Diogo", "119591557");
        Cliente cliente2 = new Cliente(12L, "Malcon", "1195843957");
        Cliente cliente3 = new Cliente(13L, "Danilo", "1195963145957");
        clienteRepository.saveAll(List.of(cliente1, cliente1, cliente1));

        Cabeleireiro cabeleireiro1 = new Cabeleireiro(11L, "Robert");
        Cabeleireiro cabeleireiro2 = new Cabeleireiro(12L, "Renato");
        Cabeleireiro cabeleireiro3 = new Cabeleireiro(13L, "Omar");
        cabeleireiroRepository.saveAll(List.of(cabeleireiro1, cabeleireiro2, cabeleireiro3));

        LocalDateTime dataHora1 = LocalDateTime.parse("2024-03-01T10:15:30");
        LocalDateTime dataHora2 = LocalDateTime.parse("2024-03-02T10:15:30");
        LocalDateTime dataHora3 = LocalDateTime.parse("2024-03-03T10:15:30");

        Agendamento agendamento1 = new Agendamento(null, cliente1, cabeleireiro1, dataHora1);
        Agendamento agendamento2 = new Agendamento(null, cliente2, cabeleireiro2, dataHora2);
        Agendamento agendamento3 = new Agendamento(null, cliente3, cabeleireiro3, dataHora3);
        if (!agendamentoRepository.existsByClienteAndCabeleireiroAndDataHora(agendamento1.getCliente(), agendamento1.getCabeleireiro(), agendamento1.getDataHora())) {
            agendamentoRepository.save(agendamento1);
        }
        if (!agendamentoRepository.existsByClienteAndCabeleireiroAndDataHora(agendamento2.getCliente(), agendamento2.getCabeleireiro(), agendamento2.getDataHora())) {
            agendamentoRepository.save(agendamento2);
        }
        if (!agendamentoRepository.existsByClienteAndCabeleireiroAndDataHora(agendamento3.getCliente(), agendamento3.getCabeleireiro(), agendamento3.getDataHora())) {
            agendamentoRepository.save(agendamento3);
        }
    }
}
